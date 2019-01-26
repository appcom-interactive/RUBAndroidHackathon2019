package eu.appcom.rubhackathon.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import eu.appcom.rubhackathon.HackathonApplication
import eu.appcom.rubhackathon.utils.Constants
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
@Module(includes = [AndroidSupportInjectionModule::class, ActivityBuilder::class])
object ApplicationModule {

  @JvmStatic
  @Provides
  @Singleton
  @Named(Constants.APPLICATION)
  internal fun bindApplicationContext(application: HackathonApplication): Context = application

}