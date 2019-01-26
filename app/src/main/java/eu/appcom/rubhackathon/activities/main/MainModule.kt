package eu.appcom.rubhackathon.activities.main

import dagger.Module
import dagger.Provides
import eu.appcom.rubhackathon.annotations.PerActivity

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
@Module
object MainModule {

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideMainPresenter(presenter: MainPresenterImpl): MainContract.MainPresenter = presenter

}