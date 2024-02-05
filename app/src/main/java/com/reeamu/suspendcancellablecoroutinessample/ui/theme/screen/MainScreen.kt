package com.reeamu.suspendcancellablecoroutinessample.ui.theme.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.reeamu.suspendcancellablecoroutinessample.MainViewModel
import com.reeamu.suspendcancellablecoroutinessample.events.CallbackEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {

            ClickButton(
                onClick = { viewModel.onEvent(CallbackEvents.ExecuteCancellableCoroutines) },
                title = "One Time Callback"
            )
            val cancellableResult = viewModel.uiState.collectAsState().value
            Message(cancellableResult.message)

            ClickButton(
                onClick = { viewModel.onEvent(CallbackEvents.ExecuteCallbackFlow) },
                title = "Multiple Callbacks"
            )
            val networkStatus = viewModel.networkState.collectAsState().value
            Message(networkStatus.toString())
        }

    }
}


@Composable
fun Message(message: String, modifier: Modifier = Modifier) {
    Text(
        text = "$message!",
        modifier = modifier
    )
}

@Composable
fun ClickButton(onClick: () -> Unit, title: String, modifier: Modifier = Modifier) {
    Button(onClick = onClick) {
        Text(
            text = "$title!",
            modifier = modifier
        )
    }
}



