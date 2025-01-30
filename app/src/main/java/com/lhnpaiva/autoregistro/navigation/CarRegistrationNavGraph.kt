package com.lhnpaiva.autoregistro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.lhnpaiva.autoregistro.routes.CreditRenegotiationRoutes
import com.lhnpaiva.autoregistro.routes.NavGraphs
import com.lhnpaiva.autoregistro.routes.addHomeNavGraph
import com.lhnpaiva.autoregistro.routes.addLoginNavGraph

@Composable
internal fun CarRegistrationNavGraph(
    navController: NavHostController,
    initialRoute: CreditRenegotiationRoutes
) {
    val startDestination = when (initialRoute) {
        CreditRenegotiationRoutes.HOME -> NavGraphs.HOME
        CreditRenegotiationRoutes.LOGIN -> NavGraphs.LOGIN
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        addLoginNavGraph(navController) {
            navController.navigate(NavGraphs.HOME)
        }

        addHomeNavGraph(navController)
    }
}


