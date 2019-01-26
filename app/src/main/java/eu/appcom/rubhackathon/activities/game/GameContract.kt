package eu.appcom.rubhackathon.activities.game

import eu.appcom.rubhackathon.base.BaseContract

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
interface GameContract {

  interface GameView : BaseContract.BaseView {

    fun up()

    fun down()
  }

  interface GamePresenter : BaseContract.BasePresenter
}