package eu.appcom.rubhackathon.controllers

import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class FirebaseDatabaseControllerImpl @Inject constructor() : FirebaseDatabaseController {

  private val db: FirebaseDatabase by lazy {
    FirebaseDatabase.getInstance()
  }

  override fun connectToDatabase() {
//    val ref = FirebaseDatabase.getInstance()
//    val myRef = database.getReference("message")
//    myRef.setValue("test")
  }

  override fun saveCommand(action: String) {
    val ref = db.reference
    ref.child("action").setValue("nils")
  }
}