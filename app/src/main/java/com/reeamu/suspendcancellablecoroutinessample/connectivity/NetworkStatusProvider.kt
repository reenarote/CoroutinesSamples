package com.reeamu.suspendcancellablecoroutinessample.connectivity

import kotlinx.coroutines.flow.Flow

interface NetworkStatusProvider {
    fun networkStatus(): Flow<NetworkStatus>
}