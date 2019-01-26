package eu.appcom.rubhackathon.activities.main

import android.os.Bundle
import eu.appcom.rubhackathon.R
import eu.appcom.rubhackathon.activities.game.GameActivity
import eu.appcom.rubhackathon.base.BaseActivity
import eu.appcom.rubhackathon.base.BaseContract
import eu.appcom.rubhackathon.controllers.BluetoothController
import eu.appcom.rubhackathon.extensions.launchActivity
import eu.appcom.rubhackathon.extensions.onClick
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.MainView {

  @Inject lateinit var presenter: MainContract.MainPresenter
  @Inject lateinit var bluetoothController: BluetoothController

  override fun providePresenter(): BaseContract.BasePresenter = presenter

  override fun provideLayoutResId(): Int = R.layout.activity_main

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val observeSubscription = bluetoothController.scanDevices()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())

    observeSubscription.subscribe({ Timber.d(it.toString()) }, { Timber.e(it) })

    setSupportActionBar(main_toolbar)

    main_game_button.onClick {
      launchActivity<GameActivity> {}
    }
  }

  override fun onResume() {
    super.onResume()
    talk_button.setOnClickListener {
      presenter.startSpeechRecognizer()
    }

    stop_button.setOnClickListener {
      presenter.stopSpeechRecognizer()
    }
  }

  override fun setText(action: String) {
    textView.text = action
  }
}
