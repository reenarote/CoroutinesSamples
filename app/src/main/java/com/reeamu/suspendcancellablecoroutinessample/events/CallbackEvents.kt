package com.reeamu.suspendcancellablecoroutinessample.events

sealed class CallbackEvents {
    object ExecuteCancellableCoroutines : CallbackEvents()
    object ExecuteCallbackFlow : CallbackEvents()
}