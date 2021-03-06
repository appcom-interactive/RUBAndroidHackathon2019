package eu.appcom.rubhackathon.activities.control

import android.annotation.SuppressLint
import eu.appcom.rubhackathon.base.BasePresenterImpl
import eu.appcom.rubhackathon.controllers.CommandController
import eu.appcom.rubhackathon.controllers.FirebaseDatabaseController
import eu.appcom.rubhackathon.controllers.SpeechController
import javax.inject.Inject

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class ControlPresenterImpl @Inject constructor() : BasePresenterImpl(), ControlContract.ControlPresenter {

  @Inject
  lateinit var view: ControlContract.ControlView

  @Inject
  lateinit var speechController: SpeechController

  @Inject
  lateinit var firebaseDatabaseController: FirebaseDatabaseController

  @Inject
  lateinit var commandController: CommandController

  private fun passToView(text: String) {
    commandController.doAction(text)
    view.setText(text)
  }

  @SuppressLint("CheckResult")
  override fun startSpeechRecognizer() {
    if (speechController.isRecognitionAvailable) {
      speechController.startSpeechRecognizer()

      speechController.observe().subscribe {
        firebaseDatabaseController.saveCommand(it)
        passToView(it)
      }
    }
  }

  override fun stopSpeechRecognizer() {
    if (speechController.isRecognitionAvailable) {
      speechController.stopSpeechRecognizer()
    }
  }
}