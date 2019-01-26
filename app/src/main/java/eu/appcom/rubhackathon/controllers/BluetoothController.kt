package eu.appcom.rubhackathon.controllers

import android.bluetooth.BluetoothDevice
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
interface BluetoothController {

  fun scanDevices(): Observable<BluetoothDevice>

  fun pairDevice(pairDevice: BluetoothDevice): Single<BluetoothDevice>

  fun connectDevice(connectDevice: BluetoothDevice): Single<BluetoothDevice>

  fun observeMessages(): Observable<String>

  fun sendMessage(message: String): Completable
}
