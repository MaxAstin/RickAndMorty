package com.bunbeauty.rickandmorty.view_model

import androidx.lifecycle.ViewModel
import com.bunbeauty.rickandmorty.domain.Result
import com.bunbeauty.rickandmorty.state.State

abstract class BaseViewModel: ViewModel() {


    fun <DM, T> Result<DM>.toState(map: (DM) -> T): State<T> {
        return when (this) {
            is Result.Success -> {
                State.Success(map(data))
            }
            is Result.Error -> {
                State.Error(message)
            }
        }
    }
}