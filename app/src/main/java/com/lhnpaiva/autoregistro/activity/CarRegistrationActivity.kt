package com.lhnpaiva.autoregistro.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.lhnpaiva.autoregistro.navigation.CarRegistrationNavGraph
import com.lhnpaiva.autoregistro.routes.CreditRenegotiationRoutes

class CarRegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    CarRegistrationNavGraph(
                        navController = rememberNavController(),
                        initialRoute = CreditRenegotiationRoutes.HOME
                    )
                }
            }
        }
    }
}
