package eu.appcom.rubhackathon.activities.settings

import eu.appcom.rubhackathon.R
import eu.appcom.rubhackathon.base.BaseActivity
import eu.appcom.rubhackathon.base.BaseContract
import javax.inject.Inject

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class SettingsActivity : BaseActivity(), SettingsContract.SettingsView {

  @Inject
  lateinit var presenter: SettingsContract.SettingsPresenter

  override fun providePresenter(): BaseContract.BasePresenter = presenter

  override fun provideLayoutResId(): Int = R.layout.activity_settings

  override fun onStart() {
    super.onStart()
  }

  override fun onPause() {
    super.onPause()


  }

}