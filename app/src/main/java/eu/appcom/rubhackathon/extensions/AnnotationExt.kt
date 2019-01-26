package eu.appcom.rubhackathon.extensions

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import eu.appcom.rubhackathon.annotations.Layout
import eu.appcom.rubhackathon.annotations.Title
import eu.appcom.rubhackathon.base.BaseActivity
import timber.log.Timber

/**
 * Created by appcom interactive GmbH on 26.01.19.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */

fun AppCompatActivity.setLayout() {
  val annotation = this.javaClass.annotations.find {
    it is Layout
  }

  if (annotation != null) {
    this.setContentView((annotation as Layout).value)
  } else {
    when ((this as BaseActivity).provideLayoutResId()) {
      0 -> {
        throw IllegalStateException("No layout resource found")
      }
      else -> {
        this.setContentView(this.provideLayoutResId())
      }
    }
  }
}

fun AppCompatActivity.setTitle() {
  val annotation = this.javaClass.getAnnotation(Title::class.java)
  if (annotation != null) {
    setTitleToActivity(this, (annotation as Title).value)
  } else {
    setTitleToActivity(this, -1)
  }
}

private fun setTitleToActivity(activity: AppCompatActivity, stringRes: Int) {
  if (stringRes > 0) {
    Timber.d("set title res $stringRes")
    activity.setTitle(stringRes)
  } else if (stringRes == 0) {
    activity.title = ""
  }
}

fun Fragment.setTitle(resId: Int) {
  this.activity?.let { setTitleToActivity(it as AppCompatActivity, resId) }
}

fun getLayout(inflater: LayoutInflater, resId: Int): View? {
  return if (resId != 0) {
    inflater.inflate(resId, null)
  } else {
    throw IllegalStateException("No layout resource found")
  }
}