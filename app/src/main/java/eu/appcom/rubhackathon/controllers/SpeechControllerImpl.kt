package eu.appcom.rubhackathon.controllers

import android.speech.RecognitionListener
import javax.inject.Inject

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class SpeechControllerImpl @Inject constructor() : SpeechController {

  override val isRecognitionAvailable: Boolean
    get() = false

  override fun startSpeechRecognizer() {

  }

  override fun stopSpeechRecognizer() {

  }
}
