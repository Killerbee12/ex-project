package com.example.shared.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {
    private val pendingJobs = Job()
    val viewState = MutableLiveData<ViewState>()

    override val coroutineContext: CoroutineContext
        get() = (viewModelScope.coroutineContext + pendingJobs)

    override fun onCleared() {
        super.onCleared()
        pendingJobs.cancel()
    }
}