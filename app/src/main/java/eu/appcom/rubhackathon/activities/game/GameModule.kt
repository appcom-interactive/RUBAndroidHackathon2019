package eu.appcom.rubhackathon.activities.game

import dagger.Module
import dagger.Provides
import eu.appcom.rubhackathon.annotations.PerActivity
import eu.appcom.rubhackathon.base.BaseActivity
import eu.appcom.rubhackathon.di.ActivityModule
import eu.appcom.rubhackathon.models.Flag

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
@Module(includes = [ActivityModule::class])
object GameModule {

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideBaseActivity(activity: GameActivity): BaseActivity = activity

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideView(activity: GameActivity): GameContract.GameView = activity

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideGamePresenter(presenterImpl: GamePresenterImpl): GameContract.GamePresenter = presenterImpl

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideFlag(activity: GameActivity): Flag = Flag(activity.intent.getBooleanExtra("tag", false))
}