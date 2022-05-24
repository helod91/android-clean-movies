package com.omdb.cleanmovies.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : ViewState, INTENT : ViewIntent, ACTION : ViewAction> :
    ViewModel() {

    val state = MutableLiveData<STATE>()

    fun dispatchIntent(intent: INTENT) {
        handleAction(intentToAction(intent))
    }

    protected fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    protected abstract fun intentToAction(intent: INTENT): ACTION
    protected abstract fun handleAction(action: ACTION)
}