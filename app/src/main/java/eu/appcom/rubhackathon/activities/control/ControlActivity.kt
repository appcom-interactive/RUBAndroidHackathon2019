package eu.appcom.rubhackathon.activities.control

import android.os.Bundle
import eu.appcom.rubhackathon.R
import eu.appcom.rubhackathon.base.BaseActivity
import eu.appcom.rubhackathon.base.BaseContract
import kotlinx.android.synthetic.main.activity_control.control_command_info_textview
import kotlinx.android.synthetic.main.activity_control.control_stop_button
import kotlinx.android.synthetic.main.activity_control.control_talk_button

import javax.inject.Inject

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class ControlActivity : BaseActivity(), ControlContract.ControlView {

  @Inject lateinit var presenter: ControlContract.ControlPresenter

  override fun providePresenter(): BaseContract.BasePresenter = presenter

  override fun provideLayoutResId(): Int = R.layout.activity_control

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    control_talk_button.setOnClickListener {
      presenter.startSpeechRecognizer()
    }

    control_stop_button.setOnClickListener {
      presenter.stopSpeechRecognizer()
    }
  }

  override fun setText(action: String) {
    control_command_info_textview.text = action
  }
}