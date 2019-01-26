package eu.appcom.rubhackathon.activities.main

import eu.appcom.rubhackathon.base.BasePresenterImpl
import eu.appcom.rubhackathon.controllers.SpeechController
import javax.inject.Inject

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class MainPresenterImpl @Inject constructor() : BasePresenterImpl(), MainContract.MainPresenter {

  @Inject lateinit var speechController: SpeechController

  @Inject
  lateinit var view: MainContract.MainView

  override fun startSpeechRecognizer() {
    if(speechController.isRecognitionAvailable){
      speechController.startSpeechRecognizer()

      speechController.observe().subscribe(view::setText)
    }
  }

  override fun stopSpeechRecognizer() {
    if(speechController.isRecognitionAvailable){
      speechController.stopSpeechRecognizer()
    }
  }

}