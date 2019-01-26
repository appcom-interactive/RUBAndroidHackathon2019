package eu.appcom.rubhackathon.controllers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.SpeechRecognizer.RESULTS_RECOGNITION
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
class SpeechControllerImpl @Inject constructor() : SpeechController, RecognitionListener {

  companion object {
    const val MAX_RESULTS = 5
  }

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
    speechRecognizer.setRecognitionListener(this)

    subject = BehaviorSubject.create()

    startListing()

  }

  fun startListing() {
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.packageName)

    intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
    intent.putExtra("android.speech.extra.DICTATION_MODE", true)

    intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 5000000)
    intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 5000000)

//    intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, MAX_RESULTS)
    speechRecognizer.startListening(intent)
  }

  override fun observe(): Observable<String> = subject

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
//    Timber.d("SpeechRecongnizer partialResults %s", partialResults.toString())

//    Timber.d("partial ${getResultsFromBundle(partialResults)}")
    compute(getResultsFromBundle(partialResults)[0])

  }

  private fun compute(text: String) {
    val list = text.split(" ")
    val lastItem = list.last()
    if (list.size > 1) {
      val beforeItem = list[list.lastIndex - 1]
      if (lastItem != beforeItem) {
        Timber.d("action: $lastItem")
        send(lastItem)
      }
    } else {
      Timber.d("action: $lastItem")
      send(lastItem)
    }
  }

  private fun send(text: String) {
    subject.onNext(text)
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
//    startListing()
  }

  override fun onResults(results: Bundle?) {
    Timber.d("SpeechRecongnizer onResults %s", results.toString())

    getResultsFromBundle(results)?.forEach {
      Timber.d("SpeechRecongnizer Result: %s", it)
    }

//    startListing()
  }

  private fun getResultsFromBundle(results: Bundle?): ArrayList<String> {
    results?.let {
      val list = results.getStringArrayList(RESULTS_RECOGNITION)

      list?.let {
        return list
      }
    }
//    if (results != null) {
//      return results.getStringArrayList(RESULTS_RECOGNITION)
//    }
    return arrayListOf()
  }

}
