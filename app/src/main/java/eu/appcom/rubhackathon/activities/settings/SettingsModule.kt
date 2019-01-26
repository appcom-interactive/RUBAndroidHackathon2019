package eu.appcom.rubhackathon.activities.settings

import dagger.Module
import dagger.Provides
import eu.appcom.rubhackathon.activities.settings.interactors.ConnectionInteractor
import eu.appcom.rubhackathon.activities.settings.interactors.ConnectionInteractorImpl
import eu.appcom.rubhackathon.annotations.PerActivity
import eu.appcom.rubhackathon.base.BaseActivity
import eu.appcom.rubhackathon.di.ActivityModule

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
@Module(includes = [ActivityModule::class])
object SettingsModule {

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideBaseActivity(activity: SettingsActivity): BaseActivity = activity

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideSettingsPresenter(presenterImpl: SettingsPresenterImpl): SettingsContract.SettingsPresenter =
    presenterImpl

  @JvmStatic
  @Provides
  @PerActivity
  internal fun provideConnectionInteractor(connectionInteractorImpl: ConnectionInteractorImpl): ConnectionInteractor =
    connectionInteractorImpl
}