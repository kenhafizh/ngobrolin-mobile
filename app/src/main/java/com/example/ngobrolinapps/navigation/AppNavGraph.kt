package com.example.ngobrolinapps.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ngobrolinapps.feature.chat.ChatMessageScreen
import com.example.ngobrolinapps.feature.home.HomeScreen
import com.example.ngobrolinapps.feature.login.LoginScreen
import com.example.ngobrolinapps.feature.register.RegisterScreen
import com.example.ngobrolinapps.feature.splash.SplashScreen
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController, startDestination = Screen.Splash.route, modifier = modifier,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(modifier = modifier, onSplashFinished = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            })
        }
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route) {
                        popUpTo(navController.graph.id)
                    }
                },
                onLoginSuccess = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(navController.graph.id)
                }
            })
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                onRegisterSuccess = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            })
        }
        composable(Screen.Home.route) {
            HomeScreen(modifier = modifier)
        }
        composable(Screen.Chat.route) {
            ChatMessageScreen()
        }
    }
}