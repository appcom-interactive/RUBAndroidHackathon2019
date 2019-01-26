package eu.appcom.rubhackathon.activities.game

import android.annotation.SuppressLint
import android.widget.Toast
import eu.appcom.rubhackathon.R
import eu.appcom.rubhackathon.base.BaseActivity
import eu.appcom.rubhackathon.base.BaseContract
import eu.appcom.rubhackathon.extensions.onClick
import kotlinx.android.synthetic.main.activity_game.*
import javax.inject.Inject

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class GameActivity : BaseActivity(), GameContract.GameView {

  @Inject
  lateinit var presenter: GameContract.GamePresenter

  override fun providePresenter(): BaseContract.BasePresenter = presenter

  override fun provideLayoutResId(): Int = R.layout.activity_game

  @SuppressLint("SetJavaScriptEnabled")
  override fun onResume() {
    super.onResume()

    game_webview.settings.javaScriptEnabled = true
//    game_webview.settings.domStorageEnabled = true
//    game_webview.loadUrl("file:///android_asset/game/index.html")
    game_webview.loadUrl("http://ndwebdesign.alfahosting.org/game/index.html")

    up_button.onClick { up() }
    down_button.onClick { down() }

  }

  override fun up() {
    game_webview.evaluateJavascript("javascript: down()", null)
  }

  override fun down() {
    game_webview.evaluateJavascript("javascript: up()", null)
  }

  override fun showCommand(action: String) {
    Toast.makeText(this, action, Toast.LENGTH_SHORT).show()
  }
}