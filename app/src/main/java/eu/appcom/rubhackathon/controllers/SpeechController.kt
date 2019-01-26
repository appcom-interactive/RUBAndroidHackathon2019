package eu.appcom.rubhackathon.controllers

import android.speech.RecognitionListener

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
interface SpeechController {

  val isRecognitionAvailable: Boolean

  fun startSpeechRecognizer()

  fun stopSpeechRecognizer()
}
