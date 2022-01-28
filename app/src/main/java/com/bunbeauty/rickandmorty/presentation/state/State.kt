package com.bunbeauty.rickandmorty.presentation.state

sealed class State<T> {
    class Loading<T>: State<T>()
    data class Error<T>(val message: String): State<T>()
    data class Success<T>(val data: T): State<T>()
}
