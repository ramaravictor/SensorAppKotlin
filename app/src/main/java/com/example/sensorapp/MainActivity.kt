package com.example.sensorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sensorapp.presentation.debug.RecordRoute
import com.example.sensorapp.presentation.main.MainRoute
import com.example.sensorapp.presentation.main.MainScreen
import com.example.sensorapp.presentation.debug.RecordScreen
import com.example.sensorapp.ui.theme.SensorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val startDestination =MainRoute

        enableEdgeToEdge()
        setContent {
            SensorAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ){
                    composable<MainRoute> {
                        MainScreen(
                            navController = navController
                        )
                    }

                    composable<RecordRoute> {
                        RecordScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}