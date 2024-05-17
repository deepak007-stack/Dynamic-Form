package com.example.dynamicform.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dynamicform.ui.screens.LoginScreen
import com.example.dynamicform.ui.screens.MainScreen
import com.example.dynamicform.viewmodel.DynamicFormViewModel

@Composable
fun NavigationCompose(navController: NavHostController, viewModel: DynamicFormViewModel) {

    NavHost(navController = navController, startDestination = "login") {

        composable(route = "login") {
            LoginScreen(navController)
        }

        composable(route = "main_screen") {
            MainScreen(viewModel)
        }
    }
}