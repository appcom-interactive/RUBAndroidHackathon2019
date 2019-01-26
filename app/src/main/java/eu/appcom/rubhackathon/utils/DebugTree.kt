package eu.appcom.playground.utils

import timber.log.Timber

/**
 * author nilsdruyen
 * date 2018-12-14
 */

class DebugTree : Timber.DebugTree() {

  override fun createStackElementTag(element: StackTraceElement): String? = String.format(
    "%s %s:%s", super
      .createStackElementTag(element), element.methodName, element.lineNumber
  )
}