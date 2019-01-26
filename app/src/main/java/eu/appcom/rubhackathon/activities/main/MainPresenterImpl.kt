package eu.appcom.rubhackathon.activities.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import eu.appcom.rubhackathon.base.BasePresenterImpl
import eu.appcom.rubhackathon.controllers.FirebaseDatabaseController
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class MainPresenterImpl @Inject constructor() : BasePresenterImpl(), MainContract.MainPresenter {

  @Inject
  lateinit var view: MainContract.MainView

  @Inject
  lateinit var firebaseDatabaseController: FirebaseDatabaseController

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun init() {
    firebaseDatabaseController.getCommand()
    firebaseDatabaseController.observe().subscribe { Timber.d("db $it") }
  }

  override fun writeData(text: String) {
    firebaseDatabaseController.saveCommand(text)
  }
}