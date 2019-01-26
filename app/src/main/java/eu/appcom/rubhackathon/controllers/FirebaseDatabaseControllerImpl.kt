package eu.appcom.rubhackathon.controllers

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import eu.appcom.rubhackathon.models.Action
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject

class FirebaseDatabaseControllerImpl @Inject constructor() : FirebaseDatabaseController {

  private val subject: BehaviorSubject<String> = BehaviorSubject.create()

  private val db: FirebaseDatabase by lazy {
    FirebaseDatabase.getInstance()
  }

  override fun saveCommand(action: String) {
    val ref = db.reference
    ref.child("action").setValue(Action(action, System.currentTimeMillis()))
  }

  override fun getCommand() {
    val ref = db.reference.child("action")
    val postListener = object : ValueEventListener {
      override fun onDataChange(dataSnapshot: DataSnapshot) {
        // Get Post object and use the values to update the UI
        val post = dataSnapshot.getValue(Action::class.java)
        post?.let { it.name?.let { it1 -> subject.onNext(it1) } }
      }

      override fun onCancelled(databaseError: DatabaseError) {
        // Getting Post failed, log a message
        Timber.d("loadPost:onCancelled: %s", databaseError.toException())
      }
    }
    ref.addValueEventListener(postListener)
  }

  override fun observe(): Observable<String> = subject
}