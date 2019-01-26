package eu.appcom.rubhackathon.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import eu.appcom.rubhackathon.extensions.getLayout
import eu.appcom.rubhackathon.extensions.setTitle

/**
 * Created by appcom interactive GmbH on 2019-01-25.
 * Copyright © 2019 appcom interactive GmbH. All rights reserved.
 */
abstract class BaseFragment : DaggerFragment(), BaseContract.BaseView {
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return getLayout(inflater, provideLayoutResId())
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
    lifecycle.addObserver(providePresenter())
  }

  override fun onResume() {
    super.onResume()
    setTitle(provideTitleResId())
  }

  override fun onDestroyView() {
    super.onDestroyView()
    lifecycle.removeObserver(providePresenter())
  }

  abstract fun initViews()

  abstract fun providePresenter(): BaseContract.BasePresenter
}