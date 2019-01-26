package eu.appcom.rubhackathon.activities.main

import eu.appcom.rubhackathon.base.BaseContract

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
interface MainContract {

  interface MainView : BaseContract.BaseView

  interface MainPresenter : BaseContract.BasePresenter {
    fun writeData(text: String)
  }
}