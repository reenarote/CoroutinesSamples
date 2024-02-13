package com.reeamu.suspendcancellablecoroutinessample.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.reeamu.suspendcancellablecoroutinessample.MainViewModel
import com.reeamu.suspendcancellablecoroutinessample.connectivity.NetworkStatusProvider
import com.reeamu.suspendcancellablecoroutinessample.connectivity.NetworkStatusProviderImpl
import com.reeamu.suspendcancellablecoroutinessample.data.GetUserDetailsRepository
import com.reeamu.suspendcancellablecoroutinessample.data.GetUserDetailsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get(), get()) }
    single<GetUserDetailsRepository> { GetUserDetailsRepositoryImpl() }
    single<NetworkStatusProvider> { NetworkStatusProviderImpl(get(),get()) }
    single<ConnectivityManager> { androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
    single<NetworkRequest> {
        NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
    }
}