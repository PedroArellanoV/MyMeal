package com.example.mymealsproyect.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymealsproyect.domain.GetUserInformationUseCase
import com.example.mymealsproyect.domain.InsertUserInformationUseCase
import com.example.mymealsproyect.domain.model.UiUserInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val insertUserInformationUseCase: InsertUserInformationUseCase
): ViewModel() {

    val userInformation = MutableLiveData<UiUserInformation>()


    fun getUserInformation(){
        viewModelScope.launch {
            val information = getUserInformationUseCase()
            userInformation.postValue(information)
        }
    }

    fun insertUserInformation(userToInsert: UiUserInformation){
        viewModelScope.launch {
            insertUserInformationUseCase(userToInsert)
        }
    }
}