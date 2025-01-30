package com.lhnpaiva.autoregistro.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.lhnpaiva.autoregistro.presentation.login.LoginScreen
import com.lhnpaiva.autoregistro.presentation.register.RegisterScreen

internal fun NavGraphBuilder.addLoginNavGraph(
    navController: NavHostController,
    navigateToHome: () -> Unit
) {
    navigation(
        route = NavGraphs.LOGIN,
        startDestination = Routes.LOGIN
    ) {
        composable(
            route = Routes.LOGIN
        ) {
            LoginScreen(
                navigateToRegister = { navController.navigate(Routes.REGISTER) },
                navigateToHome = { navigateToHome() })
        }
        composable(route = Routes.REGISTER) {
            RegisterScreen(navigateToHome = { navigateToHome() })
        }
    }
}