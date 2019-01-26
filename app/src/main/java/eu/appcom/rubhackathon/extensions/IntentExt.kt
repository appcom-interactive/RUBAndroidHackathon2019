package eu.appcom.rubhackathon.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */

inline fun <reified T : Any> Activity.launchActivity(
  finish: Boolean = false, options: Bundle? = null,
  noinline init: Intent.() -> Unit = {}
) {
  val intent = newIntent<T>(this)
  intent.init()
  startActivity(intent, options)
  if (finish) this.finish()
}

inline fun <reified T : Any> Activity.launchForResultActivity(
  requestCode: Int = -1, options: Bundle? = null,
  noinline init: Intent.() -> Unit = {}
) {
  val intent = newIntent<T>(this)
  intent.init()
  startActivityForResult(intent, requestCode, options)
}

inline fun <reified T : Any> Context.launchActivity(options: Bundle? = null, noinline init: Intent.() -> Unit = {}) {
  val intent = newIntent<T>(this)
  intent.init()
  startActivity(intent, options)
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)