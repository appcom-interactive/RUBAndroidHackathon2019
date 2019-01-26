package eu.appcom.rubhackathon

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import eu.appcom.playground.utils.ReleaseTree
import eu.appcom.rubhackathon.di.DaggerApplicationComponent
import timber.log.Timber

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class HackathonApplication : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerApplicationComponent.builder()
    .create(this)

  override fun onCreate() {
    super.onCreate()

    plantTimber()
  }

  private fun plantTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      Timber.plant(ReleaseTree())
    }
  }

}