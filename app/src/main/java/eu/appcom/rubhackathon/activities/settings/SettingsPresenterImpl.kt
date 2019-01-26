package eu.appcom.rubhackathon.activities.settings

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import eu.appcom.rubhackathon.activities.settings.interactors.ConnectionInteractor
import eu.appcom.rubhackathon.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class SettingsPresenterImpl @Inject constructor() : BasePresenterImpl(), SettingsContract.SettingsPresenter {

  @Inject
  lateinit var connectionInteractor: ConnectionInteractor

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun init() {
    connectionInteractor.execute()
  }
}