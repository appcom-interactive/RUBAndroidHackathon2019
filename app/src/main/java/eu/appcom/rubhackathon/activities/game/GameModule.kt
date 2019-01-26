package eu.appcom.rubhackathon.activities.game

import dagger.Module
import dagger.Provides
import eu.appcom.rubhackathon.annotations.PerActivity
import eu.appcom.rubhackathon.di.ActivityModule

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
@Module(includes = [ActivityModule::class])
object GameModule {

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideGamePresenter(presenterImpl: GamePresenterImpl): GameContract.GamePresenter = presenterImpl
}