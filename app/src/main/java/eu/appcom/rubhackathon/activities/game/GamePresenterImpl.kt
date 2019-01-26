package eu.appcom.rubhackathon.activities.game

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle.Event.ON_CREATE
import androidx.lifecycle.OnLifecycleEvent
import eu.appcom.rubhackathon.base.BasePresenterImpl
import eu.appcom.rubhackathon.controllers.FirebaseDatabaseController
import javax.inject.Inject

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class GamePresenterImpl @Inject constructor() : BasePresenterImpl(), GameContract.GamePresenter {


  @Inject
  lateinit var view: GameContract.GameView

  @Inject
  lateinit var firebaseDatabaseController: FirebaseDatabaseController

 @SuppressLint("CheckResult")
 @OnLifecycleEvent(ON_CREATE)
 fun subscribeToCommandsValues(){
   firebaseDatabaseController.getCommand()
   firebaseDatabaseController.observe().subscribe{
     executeCommand(it)
   }
 }

  fun executeCommand(action: String){
    view.showCommand(action)
  }

}