package com.example.ngobrolinapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.ngobrolinapps.navigation.AppNavGraph
import com.example.ngobrolinapps.ui.theme.NgobrolinAppsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NgobrolinAppsTheme {
                AppNavGraph(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

