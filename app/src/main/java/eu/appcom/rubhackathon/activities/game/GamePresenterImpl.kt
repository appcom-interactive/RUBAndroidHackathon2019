package eu.appcom.rubhackathon.activities.game

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import eu.appcom.rubhackathon.base.BasePresenterImpl
import eu.appcom.rubhackathon.controllers.CommandController
import eu.appcom.rubhackathon.controllers.FirebaseDatabaseController
import eu.appcom.rubhackathon.controllers.NoiseController
import eu.appcom.rubhackathon.controllers.SpeechController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
  @Inject
  lateinit var noiseController: NoiseController

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

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun init() {
    noiseController.start()
//    if (speechController.isRecognitionAvailable) {
//      speechController.startSpeechRecognizer()
//      speechController.observe().subscribe(this::act)
//    }
  }

  override fun onReady() {
    noiseController.observe().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ num ->
      checkNoise(num)
    }, { t ->
      Timber.e("error ${t.localizedMessage}")
    })
  }

  private fun checkNoise(noise: Int) {
//    Timber.d("noise: $noise")
    if (noise > 25000) {
      Timber.d("up")
      view.down()
    } else if (noise in 5001..9999) {
      Timber.d("down")
      view.up()
    }
  }

  private fun act(text: String) {
    val option = commandController.translate(text)
    Timber.d("action $option")
    if (option == 0) {
      view.down()
    } else if (option == 1) {
      view.up()
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun stop() {
//    if (speechController.isRecognitionAvailable) {
//      speechController.stopSpeechRecognizer()
//    }
  }

}