package com.reeamu.suspendcancellablecoroutinessample.di

import android.content.Context
import android.net.ConnectivityManager
import com.reeamu.suspendcancellablecoroutinessample.MainViewModel
import com.reeamu.suspendcancellablecoroutinessample.connectivity.NetworkStatusProvider
import com.reeamu.suspendcancellablecoroutinessample.connectivity.NetworkStatusProviderImpl
import com.reeamu.suspendcancellablecoroutinessample.data.GetUserDetailsRepository
import com.reeamu.suspendcancellablecoroutinessample.data.GetUserDetailsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get(),get()) }
    single<GetUserDetailsRepository> { GetUserDetailsRepositoryImpl() }
    single<NetworkStatusProvider> { NetworkStatusProviderImpl(get()) }
    single<ConnectivityManager> { androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
}