package com.example.mymealsproyect.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.mymealsproyect.domain.model.UiUserInformation
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.EMAIL
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.FIRSTNAME
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.LASTNAME
import com.example.mymealsproyect.ui.view.LoginFragment.Companion.USERNAME
import com.example.mymealsproyect.utils.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserFragmentViewModel @Inject constructor(): ViewModel() {

    fun getUserInformation(context: Context): UiUserInformation {

        val pref = PreferenceManager(context)
        val email = pref.getStringPref(EMAIL)
        val firstname = pref.getStringPref(FIRSTNAME)
        val lastname = pref.getStringPref(LASTNAME)
        val username = pref.getStringPref(USERNAME)

        return UiUserInformation(firstname, lastname, username, email)
    }

}