package com.bunbeauty.rickandmorty.ui.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseActivity : AppCompatActivity() {

    fun <T> Flow<T>.launchWhenStarted(block: suspend (T) -> Unit) {
        flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).onEach {
            block(it)
        }.launchIn(lifecycleScope)
    }
}