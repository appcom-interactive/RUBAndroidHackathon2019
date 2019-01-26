package eu.appcom.rubhackathon.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber

/**
 * author nilsdruyen
 * date 2018-12-14
 */
abstract class BasePresenterImpl : BaseContract.BasePresenter {

//  @Inject
//  lateinit var snackBarController: SnackBarController
//
//  private var parentJob: Job = Job()
//  private var backgroundContext: CoroutineContext = Dispatchers.Default
//  private var foregroundContext: CoroutineContext = Dispatchers.Main

//  override val coroutineContext: CoroutineContext
//    get() = foregroundContext + parentJob

  fun onError(throwable: Throwable) {
    Timber.e("ERROR: %s", throwable.message)
  }

//  fun <T> run(
//    execution: suspend () -> T,
//    onError: (Throwable) -> Unit = this::onError,
//    onComplete: (T) -> Unit
//  ) {
//    parentJob.cancel()
//    parentJob = Job()
//
//    launch(coroutineContext, CoroutineStart.DEFAULT) {
//      try {
//        val result = withContext(backgroundContext) {
//          execution()
//        }
//        onComplete.invoke(result)
//      } catch (e: CancellationException) {
//        Timber.d("canceled by user")
//      } catch (e: Exception) {
//        if (e is UnknownHostException) {
////          snackBarController.showMessage(R.string.no_internet_connection)
//        }
//        onError(e)
//      }
//    }
//  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun stopCoroutines() {
//    unsubscribe()
  }

//  private fun unsubscribe() {
//    parentJob.cancel()
//  }

}