package eu.appcom.rubhackathon.activities.main

import dagger.Module
import dagger.Provides
import eu.appcom.rubhackathon.annotations.PerActivity
import eu.appcom.rubhackathon.base.BaseActivity
import eu.appcom.rubhackathon.di.ActivityModule

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
@Module(includes = [ActivityModule::class])
object MainModule {

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideActivity(view: MainActivity): BaseActivity = view

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideMainPresenter(presenter: MainPresenterImpl): MainContract.MainPresenter = presenter

}