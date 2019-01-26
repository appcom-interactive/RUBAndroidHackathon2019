package eu.appcom.rubhackathon.controllers

import io.reactivex.Observable

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
interface FirebaseDatabaseController {

  fun saveCommand(action: String)

  fun getCommand()

  fun observe(): Observable<String>
}