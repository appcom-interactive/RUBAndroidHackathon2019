package eu.appcom.rubhackathon.di

import dagger.Component
import dagger.android.AndroidInjector
import eu.appcom.rubhackathon.HackathonApplication
import javax.inject.Singleton

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<HackathonApplication> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<HackathonApplication>()
}