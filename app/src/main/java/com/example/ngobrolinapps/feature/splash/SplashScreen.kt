package com.example.ngobrolinapps.feature.splash

import android.graphics.Paint
import android.text.Layout
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ngobrolinapps.R
import com.example.ngobrolinapps.ui.theme.NgobrolinAppsTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(modifier: Modifier = Modifier, onSplashFinished: () -> Unit = {}) {
    LaunchedEffect(Unit) {
        delay(2000)
        onSplashFinished()
    }

    Scaffold(modifier = modifier) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(R.drawable.bubble_top_splash),
                "Top",
                modifier = Modifier
                    .align(
                        Alignment.TopStart
                    )
                    .size(200.dp)
                    .alpha(0.5f)
            )
            Image(
                painter = painterResource(R.drawable.obrolin_logo),
                "Logo Application",
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
                    .padding(horizontal = 32.dp),
                contentScale = ContentScale.Fit
            )
            Image(
                painter = painterResource(R.drawable.bubble_bottom_splash),
                "Bottom",
                modifier = Modifier
                    .align(
                        Alignment.BottomEnd
                    )
                    .size(200.dp)
                    .alpha(0.5f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    NgobrolinAppsTheme() {
        SplashScreen()
    }
}