package eu.appcom.rubhackathon.activities.settings.interactors

import android.content.Context
import eu.appcom.rubhackathon.utils.Constants
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Named


/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
class ConnectionInteractorImpl @Inject constructor() : ConnectionInteractor {

  @Inject
  @field:Named(Constants.ACTIVITY)
  lateinit var context: Context

  override fun execute(): Completable = Completable.complete()

}