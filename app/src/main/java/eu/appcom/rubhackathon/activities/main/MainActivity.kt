package eu.appcom.rubhackathon.activities.main

import android.os.Bundle
import eu.appcom.rubhackathon.R
import eu.appcom.rubhackathon.activities.game.GameActivity
import eu.appcom.rubhackathon.base.BaseActivity
import eu.appcom.rubhackathon.base.BaseContract
import eu.appcom.rubhackathon.extensions.launchActivity
import eu.appcom.rubhackathon.extensions.onClick
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.MainView {

  @Inject
  lateinit var presenter: MainContract.MainPresenter

  override fun providePresenter(): BaseContract.BasePresenter = presenter

  override fun provideLayoutResId(): Int = R.layout.activity_main

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setSupportActionBar(main_toolbar)

    main_game_button.onClick {
      launchActivity<GameActivity> {}
    }
  }

}
