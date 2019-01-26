package eu.appcom.rubhackathon.activities.game

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import eu.appcom.rubhackathon.base.BasePresenterImpl
import eu.appcom.rubhackathon.controllers.CommandController
import eu.appcom.rubhackathon.controllers.FirebaseDatabaseController
import eu.appcom.rubhackathon.controllers.NoiseController
import eu.appcom.rubhackathon.controllers.SpeechController
import eu.appcom.rubhackathon.models.Flag
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
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

  @Inject
  lateinit var tag: Flag

  fun executeCommand(action: String) {
//    view.showCommand(action)
    act(action)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun init() {
    if (tag.flag) {
      firebaseDatabaseController.getCommand()
      firebaseDatabaseController.observe().subscribe {
        executeCommand(it)
      }
    } else {
      noiseController.start()
    }
  }

  private var disposable: Disposable? = null

  override fun onReady() {
    disposable = noiseController.observe().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
      .subscribe({ num ->
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
      view.up()
    } else if (option == 1) {
      view.down()
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun stop() {
    disposable?.dispose()
    if (tag.flag) {
    } else {
      noiseController.stop()
    }
  }

}