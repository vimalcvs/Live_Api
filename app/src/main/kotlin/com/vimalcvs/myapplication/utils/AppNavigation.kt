package com.vimalcvs.myapplication.utils


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vimalcvs.myapplication.views.HomeScreen
import com.vimalcvs.myapplication.views.SplashScreen


@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") { SplashScreen(navController) }

        composable("home") { HomeScreen(navController) }
    }
}
