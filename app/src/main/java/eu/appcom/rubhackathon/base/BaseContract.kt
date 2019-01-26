package eu.appcom.rubhackathon.base

import android.view.View
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Created by appcom interactive GmbH on 2019-01-25.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
interface BaseContract {

  interface BaseView : LifecycleOwner {

    fun provideLayoutResId(): Int

    fun provideLayoutContainer(): View? = null

    fun provideLayoutContainerId(): Int = -1

    fun provideTitleResId(): Int = -1

    fun showLoading() = Unit

    fun hideLoading() = Unit

    fun close() = Unit
  }

  interface BasePresenter : LifecycleObserver

}