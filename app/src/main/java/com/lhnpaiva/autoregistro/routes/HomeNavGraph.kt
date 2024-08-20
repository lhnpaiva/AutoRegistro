package com.lhnpaiva.autoregistro.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lhnpaiva.autoregistro.presentation.home.HomeScreen

internal fun NavGraphBuilder.addHomeNavGraph(
    navController: NavHostController,
) {
    navigation(
        route = NavGraphs.HOME,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen()
        }
    }
}