package com.reeamu.suspendcancellablecoroutinessample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.reeamu.suspendcancellablecoroutinessample.ui.theme.SuspendCancellableCoroutinesSampleTheme
import com.reeamu.suspendcancellablecoroutinessample.ui.theme.screen.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuspendCancellableCoroutinesSampleTheme {
                // A surface container using the 'background' color from the theme
              MainScreen()
            }
        }
    }
}



