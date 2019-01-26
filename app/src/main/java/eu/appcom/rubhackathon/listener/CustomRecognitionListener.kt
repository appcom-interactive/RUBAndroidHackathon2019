package eu.appcom.rubhackathon.listener

import android.os.Bundle
import android.speech.RecognitionListener

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
interface CustomSpeechListener {
  fun onPartialResults(partialResults: Bundle?)

  fun onResults(results: Bundle?)

  fun onError(error: Int)
}

class CustomRecognitionListener constructor(private val listener: CustomSpeechListener) : RecognitionListener {

  override fun onRmsChanged(rmsdB: Float) {
  }

  override fun onBufferReceived(buffer: ByteArray?) {
  }

  override fun onPartialResults(partialResults: Bundle?) {
    listener.onPartialResults(partialResults)
  }

  override fun onEvent(eventType: Int, params: Bundle?) {
  }

  override fun onBeginningOfSpeech() {
  }

  override fun onEndOfSpeech() {
  }

  override fun onError(error: Int) {
    listener.onError(error)
  }

  override fun onResults(results: Bundle?) {
    listener.onResults(results)
  }

  override fun onReadyForSpeech(params: Bundle?) {
  }
}