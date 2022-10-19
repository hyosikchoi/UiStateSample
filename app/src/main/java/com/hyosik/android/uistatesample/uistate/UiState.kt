package com.hyosik.android.uistatesample.uistate

import com.hyosik.android.uistatesample.Person

data class UiState(
    val isLoading : Boolean = false,
    val isError : Boolean = false,
    val newPersonList : List<Person>
) {

    val isEmpty : Boolean = !isLoading && !isError

    companion object {
        val UnInitialized = UiState(
            isLoading = false,
            isError = false,
            newPersonList = emptyList()
        )
    }
}