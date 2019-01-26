package eu.appcom.rubhackathon.controllers

import timber.log.Timber
import javax.inject.Inject

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class CommandControllerImpl @Inject constructor() : CommandController {

  override fun translate(text: String): Int = when (text.toLowerCase()) {
    "down" -> 1
    "hoch" -> 1
    "1" -> 1
    "0" -> 0
    "test" -> 0
    "runter" -> 0
    else -> -1
  }

  override fun doAction(text: String) = when (text.toLowerCase()) {
    "faster" -> moveFasterPokey()
    "slower" -> moveSlowerPokey()
    "top" -> jumpPokey()
    "bottom" -> duckPokey()
    else -> {
      Timber.d("unkown $text")
    }
  }

  override fun moveFasterPokey() {
    Timber.d("moveFasterPokey")
  }

  override fun moveSlowerPokey() {
    Timber.d("moveSlowerPokey")
  }

  override fun jumpPokey() {
    Timber.d("jumpPokey")
  }

  override fun duckPokey() {
    Timber.d("duckPokey")
  }
}
