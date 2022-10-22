package com.hyosik.android.uistatesample

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyosik.android.uistatesample.uistate.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

     private val _uiState : MutableStateFlow<UiState> = MutableStateFlow(UiState.UnInitialized)

     val uiState : StateFlow<UiState> = _uiState.asStateFlow()

     private var personDummyKey : Int = 1

     private val getPerson = flow<List<Person>> {
          emit((personDummyKey until (personDummyKey+PAGING_SIZE)).map { key ->
               Person(
                    name = "person$key",
                    age = key,
                    gender = "Man"
               )
          })
     }

     fun fetchPerson() = viewModelScope.launch {
         getPerson.onStart {
              _uiState.update { state -> state.copy(isLoading = true , isError = false)}
         }.catch { e ->
              _uiState.value = UiState(isError = true)
         }.collectLatest { personList ->
              _uiState.value = UiState(newPersonList = personList)
              personDummyKey += PAGING_SIZE
         }
     }

     companion object {
          const val PAGING_SIZE = 10
     }

}