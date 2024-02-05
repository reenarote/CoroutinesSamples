package com.reeamu.suspendcancellablecoroutinessample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reeamu.suspendcancellablecoroutinessample.connectivity.NetworkStatus
import com.reeamu.suspendcancellablecoroutinessample.connectivity.NetworkStatusProvider
import com.reeamu.suspendcancellablecoroutinessample.data.GetUserDetailsRepository
import com.reeamu.suspendcancellablecoroutinessample.data.UserCallback
import com.reeamu.suspendcancellablecoroutinessample.data.dto.User
import com.reeamu.suspendcancellablecoroutinessample.events.CallbackEvents
import com.reeamu.suspendcancellablecoroutinessample.state.CallbackState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class MainViewModel(
    private val repository: GetUserDetailsRepository,
    networkStatusProvider: NetworkStatusProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow(CallbackState())
    val uiState: StateFlow<CallbackState> = _uiState.asStateFlow()

    val networkState = networkStatusProvider.networkStatus().stateIn(
        initialValue = NetworkStatus.Unknown,
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )


    private fun updateUiState(state: CallbackState) {
        _uiState.update {
            it.copy(state.message)
        }
    }

    fun onEvent(events: CallbackEvents) {
        when (events) {
            is CallbackEvents.ExecuteCallbackFlow -> {
                updateUiState(CallbackState(message = networkState.value.toString()))
            }

            is CallbackEvents.ExecuteCancellableCoroutines -> {
                viewModelScope.launch {
                    getUserDetails()
                }

            }
        }
    }


    private suspend fun getUserDetails() = suspendCancellableCoroutine {
        repository.invoke(object : UserCallback {
            override fun userDetails(user: User) {
                it.resume(flowOf(user))
            }
        })
    }.collect {
        updateUiState(CallbackState(message = it.toString()))
    }
}