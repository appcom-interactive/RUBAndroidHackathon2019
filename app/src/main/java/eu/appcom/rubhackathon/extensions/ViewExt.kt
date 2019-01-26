package eu.appcom.rubhackathon.extensions

import android.view.View

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */

infix fun View.onClick(function: () -> Unit) {
  setOnClickListener { function() }
}