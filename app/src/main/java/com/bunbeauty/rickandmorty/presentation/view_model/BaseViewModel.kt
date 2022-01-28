package com.bunbeauty.rickandmorty.presentation.view_model

import androidx.lifecycle.ViewModel
import com.bunbeauty.rickandmorty.domain.Result
import com.bunbeauty.rickandmorty.presentation.state.State

abstract class BaseViewModel: ViewModel() {
    
    protected fun <DM, T> Result<DM>.toState(map: (DM) -> T): State<T> {
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