package eu.appcom.rubhackathon.base

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import eu.appcom.rubhackathon.extensions.setLayout
import eu.appcom.rubhackathon.extensions.setTitle

/**
 * Created by appcom interactive GmbH on 2019-01-25.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */

abstract class BaseActivity : DaggerAppCompatActivity(), BaseContract.BaseView {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lifecycle.addObserver(providePresenter())
    setLayout()
  }

  override fun onResume() {
    super.onResume()
    setTitle()
  }

  override fun onDestroy() {
    super.onDestroy()
    lifecycle.removeObserver(providePresenter())
  }

  abstract fun providePresenter(): BaseContract.BasePresenter

}