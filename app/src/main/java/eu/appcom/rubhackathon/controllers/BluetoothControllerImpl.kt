package eu.appcom.rubhackathon.controllers

import android.bluetooth.BluetoothDevice
import android.content.Context
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import me.aflak.bluetooth.Bluetooth
import me.aflak.bluetooth.DeviceCallback
import me.aflak.bluetooth.DiscoveryCallback
import javax.inject.Inject
import javax.inject.Named

/**
 * @author appcom interactive GmbH on 26.01.19
 */
class BluetoothControllerImpl @Inject constructor() : BluetoothController {

  @Inject @field:Named("application") lateinit var context: Context

  private val bluetooth: Bluetooth by lazy {
    Bluetooth(context).apply {
      onStart()
      enable()
    }
  }

  override fun scanDevices(): Observable<BluetoothDevice> {
    return Observable.create { emitter ->
      bluetooth.setDiscoveryCallback(object : DiscoveryCallback {
        override fun onDiscoveryStarted() {}
        override fun onDiscoveryFinished() {}
        override fun onDeviceFound(device: BluetoothDevice) {
          emitter.onNext(device)
        }

        override fun onDevicePaired(device: BluetoothDevice) {}
        override fun onDeviceUnpaired(device: BluetoothDevice) {}
        override fun onError(message: String) {
          emitter.onError(Throwable(message))
        }
      })
      bluetooth.startScanning()
    }
  }

  override fun pairDevice(pairDevice: BluetoothDevice): Single<BluetoothDevice> {
    return Single.create { emitter ->
      bluetooth.setDiscoveryCallback(object : DiscoveryCallback {
        override fun onDiscoveryStarted() {}
        override fun onDiscoveryFinished() {}
        override fun onDeviceFound(device: BluetoothDevice) {}
        override fun onDevicePaired(device: BluetoothDevice) {
          emitter.onSuccess(device)
        }

        override fun onDeviceUnpaired(device: BluetoothDevice) {}
        override fun onError(message: String) {
          emitter.onError(Throwable(message))
        }
      })
      bluetooth.pair(pairDevice)
    }
  }

  override fun connectDevice(connectDevice: BluetoothDevice): Single<BluetoothDevice> {
    return Single.create { emitter ->
      bluetooth.setDeviceCallback(object : DeviceCallback {
        override fun onDeviceConnected(device: BluetoothDevice) {
          emitter.onSuccess(device)
        }

        override fun onDeviceDisconnected(device: BluetoothDevice, message: String) {}
        override fun onMessage(message: String) {}
        override fun onError(message: String) {}
        override fun onConnectError(device: BluetoothDevice, message: String) {
          emitter.onError(Throwable(message))
        }
      })
      bluetooth.connectToDevice(connectDevice)
    }
  }

  override fun observeMessages(): Observable<String> {
    return Observable.create { emitter ->
      bluetooth.setDeviceCallback(object : DeviceCallback {
        override fun onDeviceConnected(device: BluetoothDevice) {}
        override fun onDeviceDisconnected(device: BluetoothDevice, message: String) {}
        override fun onMessage(message: String) {
          emitter.onNext(message)
        }

        override fun onError(message: String) {
          emitter.onError(Throwable(message))
        }

        override fun onConnectError(device: BluetoothDevice, message: String) {
          emitter.onError(Throwable(message))
        }
      })
    }
  }

  override fun sendMessage(message: String): Completable {
    return Completable.create { emitter ->
      bluetooth.send(message)
      emitter.onComplete()
    }
  }
}