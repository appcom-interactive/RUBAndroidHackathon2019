package eu.appcom.playground.utils

import android.util.Log
import timber.log.Timber

/**
 * author nilsdruyen
 * date 2018-12-14
 */

class ReleaseTree : Timber.DebugTree() {

  override fun isLoggable(tag: String?, priority: Int): Boolean =
    priority == Log.ERROR || priority == Log.WARN || priority == Log.INFO

  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    if (isLoggable(tag, priority)) {
      super.log(priority, tag, message, t)
    }
  }
}