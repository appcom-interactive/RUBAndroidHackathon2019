package eu.appcom.rubhackathon.activities.settings.interactors

import io.reactivex.Completable

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
interface ConnectionInteractor {

  fun execute(): Completable
}