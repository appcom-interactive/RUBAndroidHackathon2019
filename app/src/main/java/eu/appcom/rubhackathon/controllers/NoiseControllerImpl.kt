package eu.appcom.rubhackathon.controllers

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class NoiseControllerImpl @Inject constructor() : NoiseController {

  private var ar: AudioRecord? = null
  private var minSize: Int = 0

  private var disposable: Disposable? = null

  private var subject: BehaviorSubject<Int> = BehaviorSubject.create()

  override fun start() {
    minSize = AudioRecord.getMinBufferSize(8000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT)
    ar = AudioRecord(
      MediaRecorder.AudioSource.MIC,
      8000,
      AudioFormat.CHANNEL_IN_MONO,
      AudioFormat.ENCODING_PCM_16BIT,
      minSize
    )
    ar!!.startRecording()

    disposable = Observable.interval(200, TimeUnit.MILLISECONDS).map { getAmplitude() }.subscribe {
      subject.onNext(it.toInt())
    }
  }

  override fun stop() {
    disposable?.dispose()
    if (ar != null) {
      ar!!.stop()
    }
  }

  fun getAmplitude(): Double {
    val buffer = ShortArray(minSize)
    ar!!.read(buffer, 0, minSize)
    var max = 0
    for (s in buffer) {
      if (Math.abs(s.toInt()) > max) {
        max = Math.abs(s.toInt())
      }
    }
    return max.toDouble()
  }

  override fun observe(): Observable<Int> = subject
}