package eu.appcom.rubhackathon.controllers

import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback
import io.reactivex.Completable

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
interface ConnectionController {

  fun startAdvertising(): Completable

  fun stopAdvertising(): Completable

  fun startDiscovery(): Completable

  fun stopDiscovery(): Completable

  fun sendPayload(message: String): Completable
}
