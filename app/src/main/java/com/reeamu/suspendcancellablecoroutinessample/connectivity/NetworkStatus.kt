package com.reeamu.suspendcancellablecoroutinessample.connectivity

sealed class NetworkStatus {
    object Unknown : NetworkStatus()
    object Connected : NetworkStatus()
    object Disconnected : NetworkStatus()
}