package com.example.mymeal.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymeal.domain.SearchRecipeUseCase
import com.example.mymeal.domain.model.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel@Inject constructor(
    private val searchRecipeUseCase: SearchRecipeUseCase
) : ViewModel() {

    val recipesResponse = MutableLiveData<SearchResult>()

    fun searchRecipe(query: String) {
        viewModelScope.launch {
            val result = searchRecipeUseCase(query)
            if (result != null){
                recipesResponse.postValue(result!!)
            }
        }
    }
}