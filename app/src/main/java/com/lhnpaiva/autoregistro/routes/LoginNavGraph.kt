package com.lhnpaiva.autoregistro.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.lhnpaiva.autoregistro.presentation.login.LoginScreen

internal fun NavGraphBuilder.addLoginNavGraph(
    navController: NavHostController,
) {
    navigation(
        route = NavGraphs.LOGIN,
        startDestination = Routes.LOGIN
    ) {
        composable(
            route = Routes.LOGIN
        ) {
            LoginScreen()
        }
    }
}