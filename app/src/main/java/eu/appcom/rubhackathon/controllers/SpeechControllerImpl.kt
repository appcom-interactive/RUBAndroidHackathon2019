package eu.appcom.rubhackathon.controllers

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.SpeechRecognizer.*
import eu.appcom.rubhackathon.listener.CustomRecognitionListener
import eu.appcom.rubhackathon.listener.CustomSpeechListener
import eu.appcom.rubhackathon.utils.Constants.ACTIVITY
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Named

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class SpeechControllerImpl @Inject constructor() : SpeechController, CustomSpeechListener {

  private lateinit var subject: BehaviorSubject<String>

  private lateinit var speechRecognizer: SpeechRecognizer

  @Inject
  @field:Named(ACTIVITY)
  lateinit var context: Context

  override val isRecognitionAvailable: Boolean
    get() {
      val isAvailable = SpeechRecognizer.isRecognitionAvailable(context)
      Timber.d("SpeechRecongnizer is available: %s", isAvailable.toString())
      return isAvailable
    }

  override fun startSpeechRecognizer() {
    speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    speechRecognizer.setRecognitionListener(CustomRecognitionListener(this))

    subject = BehaviorSubject.create()

    startListing()
  }

  fun startListing() {
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.packageName)

    intent.putExtra("android.speech.extra.DICTATION_MODE", true)
    intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true)
    }

    intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 10000)
    intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 5000000)

//    intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, MAX_RESULTS)
    speechRecognizer.startListening(intent)
  }

  override fun stopSpeechRecognizer() {
    speechRecognizer.stopListening()
    speechRecognizer.destroy()
  }

  override fun observe(): Observable<String> = subject

  /*
      CustomRecognitionListener
   */
  override fun onPartialResults(partialResults: Bundle?) {
    getNewElementFromString(getResultsFromBundle(partialResults)[0])

  }

  override fun onError(error: Int) {
    if (error == ERROR_INSUFFICIENT_PERMISSIONS || error == ERROR_NETWORK || error == ERROR_SERVER) {
      Timber.d("SpeechRecongnizer onError do not restart")
    } else {
      Timber.d("SpeechRecongnizer onError: %s. Restart service", error.toString())
      startListing()
    }
  }

  override fun onResults(results: Bundle?) {
    Timber.d("SpeechRecongnizer onResults %s", results.toString())
    startListing()
  }

  private fun getResultsFromBundle(results: Bundle?): ArrayList<String> {
    results?.let {
      val list = results.getStringArrayList(RESULTS_RECOGNITION)

      list?.let {
        return list
      }
    }
    return arrayListOf()
  }

  private fun getNewElementFromString(text: String) {
    val list = text.split(" ")
    val lastItem = list.last()
    if (list.size > 1) {
      val beforeItem = list[list.lastIndex - 1]
      if (lastItem != beforeItem) {
        Timber.d("action: $lastItem")
        sendCommand(lastItem)
      }
    } else {
      Timber.d("action: $lastItem")
      sendCommand(lastItem)
    }
  }

  private fun sendCommand(text: String) {
    Timber.d("SpeechRecongnizer nextCommand: %s", text)
    subject.onNext(text)
  }

}
