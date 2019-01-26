package eu.appcom.rubhackathon.activities.control

import dagger.Module
import dagger.Provides
import eu.appcom.rubhackathon.annotations.PerActivity
import eu.appcom.rubhackathon.base.BaseActivity
import eu.appcom.rubhackathon.di.ActivityModule

/*
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
@Module(includes = [ActivityModule::class])
object ControlModule {

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideActivity(view: ControlActivity): BaseActivity = view

  @JvmStatic

  @Provides
  @PerActivity
  internal fun provideControlView(view: ControlActivity): ControlContract.ControlView = view

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideControlPresenter(presenter: ControlPresenterImpl): ControlContract.ControlPresenter = presenter
}