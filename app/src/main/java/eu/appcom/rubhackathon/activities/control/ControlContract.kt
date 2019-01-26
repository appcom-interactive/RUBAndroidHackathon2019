package eu.appcom.rubhackathon.activities.control

import eu.appcom.rubhackathon.base.BaseContract

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
interface ControlContract {

  interface ControlView : BaseContract.BaseView {
    fun setText(action: String)
  }

  interface ControlPresenter : BaseContract.BasePresenter {
    fun startSpeechRecognizer()

    fun stopSpeechRecognizer()
  }
}