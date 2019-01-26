package eu.appcom.rubhackathon.activities.main

import eu.appcom.rubhackathon.R
import eu.appcom.rubhackathon.base.BaseActivity
import eu.appcom.rubhackathon.base.BaseContract
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.MainView {

  @Inject
  lateinit var presenter: MainContract.MainPresenter

  override fun providePresenter(): BaseContract.BasePresenter = presenter

  override fun provideLayoutResId(): Int = R.layout.activity_main

}
