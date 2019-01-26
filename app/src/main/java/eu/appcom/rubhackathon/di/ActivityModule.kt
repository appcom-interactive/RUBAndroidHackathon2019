package eu.appcom.rubhackathon.di

import android.content.Context
import dagger.Binds
import dagger.Module
import eu.appcom.rubhackathon.annotations.PerActivity
import eu.appcom.rubhackathon.base.BaseActivity
import eu.appcom.rubhackathon.controllers.BluetoothController
import eu.appcom.rubhackathon.controllers.BluetoothControllerImpl
import eu.appcom.rubhackathon.controllers.CommandController
import eu.appcom.rubhackathon.controllers.CommandControllerImpl
import eu.appcom.rubhackathon.controllers.FirebaseDatabaseController
import eu.appcom.rubhackathon.controllers.FirebaseDatabaseControllerImpl
import eu.appcom.rubhackathon.controllers.SpeechController
import eu.appcom.rubhackathon.controllers.SpeechControllerImpl
import eu.appcom.rubhackathon.utils.Constants.ACTIVITY
import javax.inject.Named

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */

@Module(includes = [ControllerModule::class, FragmentBuilder::class])
object ActivityModule

@Module
abstract class ControllerModule {

  @Binds
  @PerActivity
  @Named(ACTIVITY)
  internal abstract fun bindActivityContext(activity: BaseActivity): Context

  @Binds
  @PerActivity
  abstract fun bindCommandController(commandControllerImpl: CommandControllerImpl): CommandController

  @Binds
  @PerActivity
  abstract fun bindSpeechController(speechControllerImpl: SpeechControllerImpl): SpeechController


  @Binds
  @PerActivity
  abstract fun bindBluetoothController(speechControllerImpl: BluetoothControllerImpl): BluetoothController

  @Binds
  @PerActivity
  abstract fun bindFirebaseDatabaseController(firebaseDatabaseControllerImpl: FirebaseDatabaseControllerImpl): FirebaseDatabaseController
}