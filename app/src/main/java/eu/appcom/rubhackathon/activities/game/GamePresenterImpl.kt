package eu.appcom.rubhackathon.activities.game

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import eu.appcom.rubhackathon.base.BasePresenterImpl
import eu.appcom.rubhackathon.controllers.CommandController
import eu.appcom.rubhackathon.controllers.FirebaseDatabaseController
import eu.appcom.rubhackathon.controllers.SpeechController
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class GamePresenterImpl @Inject constructor() : BasePresenterImpl(), GameContract.GamePresenter {

  @Inject
  lateinit var speechController: SpeechController
  @Inject
  lateinit var view: GameContract.GameView
  @Inject
  lateinit var commandController: CommandController
  @Inject
  lateinit var firebaseDatabaseController: FirebaseDatabaseController

  @SuppressLint("CheckResult")
  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun subscribeToCommandsValues() {
    firebaseDatabaseController.getCommand()
    firebaseDatabaseController.observe().subscribe {
      executeCommand(it)
    }
  }

  fun executeCommand(action: String) {
//    view.showCommand(action)
    act(action)
  }

//  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//  fun init() {
//    if (speechController.isRecognitionAvailable) {
//      speechController.startSpeechRecognizer()
//      speechController.observe().subscribe(this::act)
//    }
//  }

  private fun act(text: String) {
    val option = commandController.translate(text)
    Timber.d("action $option")
    if (option == 0) {
      view.up()
    } else if (option == 1) {
      view.down()
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun stop() {
//    if (speechController.isRecognitionAvailable) {
//      speechController.stopSpeechRecognizer()
//    }
  }

}