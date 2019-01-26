package eu.appcom.rubhackathon.activities.main

import eu.appcom.rubhackathon.R
import eu.appcom.rubhackathon.base.BaseActivity
import eu.appcom.rubhackathon.base.BaseContract
import kotlinx.android.synthetic.main.activity_main.stop_button
import kotlinx.android.synthetic.main.activity_main.talk_button
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.MainView {

  @Inject
  lateinit var presenter: MainContract.MainPresenter

  override fun providePresenter(): BaseContract.BasePresenter = presenter

  override fun provideLayoutResId(): Int = R.layout.activity_main

  override fun onResume() {
    super.onResume()
    talk_button.setOnClickListener{
      presenter.startSpeechRecognizer()
    }

    stop_button.setOnClickListener{
      presenter.stopSpeechRecognizer()
    }
  }

}
