package eu.appcom.rubhackathon.controllers

import android.content.Context
import android.os.Build
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.connection.AdvertisingOptions
import com.google.android.gms.nearby.connection.ConnectionInfo
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback
import com.google.android.gms.nearby.connection.ConnectionResolution
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo
import com.google.android.gms.nearby.connection.DiscoveryOptions
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback
import com.google.android.gms.nearby.connection.Payload
import com.google.android.gms.nearby.connection.PayloadCallback
import com.google.android.gms.nearby.connection.PayloadTransferUpdate
import com.google.android.gms.nearby.connection.Strategy
import eu.appcom.rubhackathon.BuildConfig
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

/**
 * @author appcom interactive GmbH on 26.01.19
 */
class ConnectionControllerImpl @Inject constructor() : ConnectionController {

  @Inject @field:Named("application") lateinit var context: Context

  private var connectedEndpointId = ""
  private var isAdvertising = false
  private var isDiscovery = false
  private var statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()
  private var payloadSubject: BehaviorSubject<String> = BehaviorSubject.create()

  override fun observeStatus(): Observable<Boolean> {
    return statusSubject
  }

  override fun observePayload(): Observable<String> {
    return payloadSubject
  }

  override fun startAdvertising(): Completable {
    return Completable.create { emitter ->
      if (!isAdvertising) {
        if (isDiscovery) {
          stopDiscovery().blockingAwait()
        }
        isAdvertising = true
        Timber.d("Start advertising")
        AdvertisingOptions.Builder().setStrategy(Strategy.P2P_STAR).build().let {
          Nearby.getConnectionsClient(context)
            .startAdvertising(Build.DEVICE, BuildConfig.APPLICATION_ID, connectionLifecycleCallback, it)
            .addOnSuccessListener { emitter.onComplete() }
            .addOnFailureListener { e: Exception -> emitter.onError(e) }
        }
      }
    }
  }

  override fun stopAdvertising(): Completable {
    return Completable.fromCallable {
      Timber.d("Stop advertising")
      isAdvertising = false
      Nearby.getConnectionsClient(context).stopAdvertising()
    }
  }

  override fun startDiscovery(): Completable {
    return Completable.create { emitter ->
      if (!isDiscovery) {
        if (isAdvertising) {
          stopAdvertising().blockingAwait()
        }
        isDiscovery = true
        Timber.d("Start discovery")
        DiscoveryOptions.Builder().setStrategy(Strategy.P2P_STAR).build().let {
          Nearby.getConnectionsClient(context)
            .startDiscovery(BuildConfig.APPLICATION_ID, endpointDiscoveryCallback, it)
            .addOnSuccessListener { emitter.onComplete() }
            .addOnFailureListener { e: java.lang.Exception -> emitter.onError(e) }
        }
      }
    }
  }

  override fun stopDiscovery(): Completable {
    return Completable.fromCallable {
      Timber.d("Stop discovery")
      isDiscovery = false
      Nearby.getConnectionsClient(context).stopDiscovery()
    }
  }

  override fun sendPayload(message: String): Completable {
    return Completable.fromCallable {
      Nearby.getConnectionsClient(context)
        .sendPayload(connectedEndpointId, Payload.fromBytes(message.toByteArray()))
    }
  }

  private val endpointDiscoveryCallback = object : EndpointDiscoveryCallback() {
    override fun onEndpointFound(endpointId: String, info: DiscoveredEndpointInfo) {
      // An endpoint was found. We request a connection to it.
      Nearby.getConnectionsClient(context)
        .requestConnection(Build.DEVICE, endpointId, connectionLifecycleCallback)
        .addOnSuccessListener {
          // We successfully requested a connection. Now both sides
          // must accept before the connection is established.
          connectedEndpointId = endpointId
          Timber.d("EndpointDiscoveryCallback successfull")
        }
        .addOnFailureListener {
          // Nearby Connections failed to request the connection.
        }
    }

    override fun onEndpointLost(endpointId: String) {
      // A previously discovered endpoint has gone away.
    }
  }

  private val connectionLifecycleCallback = object : ConnectionLifecycleCallback() {
    override fun onConnectionInitiated(endpointId: String, connectionInfo: ConnectionInfo) {
      // Automatically accept the connection on both sides.
      Nearby.getConnectionsClient(context).acceptConnection(endpointId, payloadCallback)
    }

    override fun onConnectionResult(endpointId: String, result: ConnectionResolution) {
      if (result.status.statusCode == ConnectionsStatusCodes.STATUS_OK) {
        // We're connected! Can now start sending and receiving data.
        if (isDiscovery) {
          isDiscovery = false
          stopDiscovery().subscribe()
        } else if (isAdvertising) {
          isAdvertising = false
          stopAdvertising().subscribe()
        }
        connectedEndpointId = endpointId
        Timber.d("ConnectionLifecycleCallback successfull")
        statusSubject.onNext(true)
      } else {
        statusSubject.onNext(false)
      }
    }

    override fun onDisconnected(endpointId: String) {
      // We've been disconnected from this endpoint. No more data can be
      // sent or received.
      Timber.d("onDisconnected")
      statusSubject.onNext(false)
    }
  }

  private val payloadCallback = object : PayloadCallback() {
    override fun onPayloadReceived(endpointId: String, payload: Payload) {
      Timber.d("Payload received")
      payload.asBytes()?.let {
        if (it.isNotEmpty()) {
          payloadSubject.onNext(String(it))
        }
      }
    }

    override fun onPayloadTransferUpdate(endpointId: String, update: PayloadTransferUpdate) {
      // Payload progress has updated.
    }
  }
}