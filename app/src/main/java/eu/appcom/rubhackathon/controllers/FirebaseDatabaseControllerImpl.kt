package eu.appcom.rubhackathon.controllers

import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class FirebaseDatabaseControllerImpl @Inject constructor() : FirebaseDatabaseController {

  override fun connectToDatabase() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("message")

    myRef.setValue("test")
  }

  override fun saveCommand(action: String) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}