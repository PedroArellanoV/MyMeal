package com.example.mymealsproyect.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.EMAIL
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.FIRSTNAME
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.LASTNAME
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.USERNAME
import com.example.mymealsproyect.utils.PreferenceManager
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor() :
    ViewModel() {

    fun insertUserInDatabase(email: String?, context: Context) {
        val firebaseDB = FirebaseFirestore.getInstance()

        firebaseDB.collection("users").document(email ?: "").get().addOnSuccessListener {

            val firstname = it.get(FIRSTNAME) as String
            val lastname = it.get(LASTNAME) as String
            val username = it.get(USERNAME) as String

            insertUser(email, firstname, lastname, username, context)
}
    }

    private fun insertUser(email: String?, firstname: String, lastname: String, username: String, context: Context){


        val pref = PreferenceManager(context)
        pref.setStringPref(EMAIL, email)
        pref.setStringPref(FIRSTNAME, firstname)
        pref.setStringPref(LASTNAME, lastname)
        pref.setStringPref(USERNAME, username)


    }
}