package eu.appcom.rubhackathon.controllers

import io.reactivex.Completable
import io.reactivex.Observable

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
interface ConnectionController {

  fun observeStatus(): Observable<Boolean>

  fun observePayload(): Observable<String>

  fun startAdvertising(): Completable

  fun stopAdvertising(): Completable

  fun startDiscovery(): Completable

  fun stopDiscovery(): Completable

  fun sendPayload(message: String): Completable
}
