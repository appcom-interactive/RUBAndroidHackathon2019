package eu.appcom.rubhackathon.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.appcom.rubhackathon.activities.control.ControlActivity
import eu.appcom.rubhackathon.activities.control.ControlModule
import eu.appcom.rubhackathon.activities.game.GameActivity
import eu.appcom.rubhackathon.activities.game.GameModule
import eu.appcom.rubhackathon.activities.main.MainActivity
import eu.appcom.rubhackathon.activities.main.MainModule
import eu.appcom.rubhackathon.activities.settings.SettingsActivity
import eu.appcom.rubhackathon.activities.settings.SettingsModule
import eu.appcom.rubhackathon.annotations.PerActivity

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
@Module
abstract class ActivityBuilder {

  @PerActivity
  @ContributesAndroidInjector(modules = [MainModule::class])
  internal abstract fun mainInjector(): MainActivity

  @PerActivity
  @ContributesAndroidInjector(modules = [GameModule::class])
  internal abstract fun gameInjector(): GameActivity

  @PerActivity
  @ContributesAndroidInjector(modules = [SettingsModule::class])
  internal abstract fun settingsInjector(): SettingsActivity

  @PerActivity
  @ContributesAndroidInjector(modules = [ControlModule::class])
  internal abstract fun controlInjector(): ControlActivity

}

@Module
abstract class FragmentBuilder {

//  @PerFragment
//  @ContributesAndroidInjector(modules = [MovieListModule::class])
//  internal abstract fun movieListInjector(): MovieListFragment

}