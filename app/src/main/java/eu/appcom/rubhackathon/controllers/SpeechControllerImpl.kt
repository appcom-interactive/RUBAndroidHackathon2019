package eu.appcom.rubhackathon.controllers

import android.content.Context
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import eu.appcom.rubhackathon.utils.Constants.ACTIVITY
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named
import android.speech.RecognizerIntent
import android.content.Intent
import android.os.Bundle
import android.speech.SpeechRecognizer.RESULTS_RECOGNITION
import java.util.ArrayList

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class SpeechControllerImpl @Inject constructor() : SpeechController, RecognitionListener {

  companion object {
    const val MAX_RESULTS = 5
  }

  private lateinit var speechRecognizer: SpeechRecognizer

  @Inject @field:Named(ACTIVITY) lateinit var context: Context

  override val isRecognitionAvailable: Boolean
    get() {
      val isAvailable = SpeechRecognizer.isRecognitionAvailable(context)
      Timber.d("SpeechRecongnizer is available: %s",isAvailable.toString())
      return isAvailable
    }

  override fun startSpeechRecognizer() {
    speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    speechRecognizer.setRecognitionListener(this)

    startListing()

  }

  fun startListing(){
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.packageName)

    intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, MAX_RESULTS)
    speechRecognizer.startListening(intent)
  }

  override fun stopSpeechRecognizer() {
    speechRecognizer.stopListening()
    speechRecognizer.destroy()
  }


  override fun onReadyForSpeech(params: Bundle?) {
   // Timber.d("SpeechRecongnizer onReadyForSpeech %s", params.toString())
  }

  override fun onRmsChanged(rmsdB: Float) {
   // Timber.d("SpeechRecongnizer onRmsChanged %s", rmsdB.toString())
  }

  override fun onBufferReceived(buffer: ByteArray?) {
  //  Timber.d("SpeechRecongnizer onBufferReceived %s", buffer.toString())
  }

  override fun onPartialResults(partialResults: Bundle?) {
    Timber.d("SpeechRecongnizer partialResults %s", partialResults.toString())
  }

  override fun onEvent(eventType: Int, params: Bundle?) {
  // Tim
    // er.d("SpeechRecongnizer onEvent %s and params: %s", eventType.toString(),params.toString())
  }

  override fun onBeginningOfSpeech() {
   // Timber.d("SpeechRecongnizer onBeginningOfSpeech")
  }

  override fun onEndOfSpeech() {
  //  Timber.d("SpeechRecongnizer onEndOfSpeech")
  }

  override fun onError(error: Int) {
    Timber.d("SpeechRecongnizer onError %s", error.toString())
    startListing()
  }

  override fun onResults(results: Bundle?) {
    Timber.d("SpeechRecongnizer onResults %s", results.toString())

    getResultsFromBundle(results)?.forEach {
      Timber.d("SpeechRecongnizer Result: %s", it)
    }

    startListing()

  }

  private fun getResultsFromBundle(results: Bundle?): ArrayList<String>? {
    if(results!= null){
      return results.getStringArrayList(RESULTS_RECOGNITION)
    }
    return arrayListOf<String>()
  }

}
