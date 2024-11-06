package com.example.sensorapp.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sensorapp.presentation.debug.RecordRoute
import kotlinx.serialization.Serializable

@Serializable
object MainRoute

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF6F6F6)), // Latar belakang yang ringan dan lembut
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header Text
                Text(
                    text = "Sensor Data",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3F51B5) // Warna biru untuk teks judul
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Card for Total Steps
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White // Warna latar belakang kartu
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Total Steps",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF3F51B5)
                        )
                        Text(
                            text = "${state.stepCounter}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Card for Accelerometer
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Accelerometer",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF3F51B5)
                        )
                        Text(
                            text = "X: ${state.accelerometerX}",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "Y: ${state.accelerometerY}",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "Z: ${state.accelerometerZ}",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Debug Button
                Button(
                    onClick = {
                        navController.navigate(RecordRoute)
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F51B5),
                        contentColor = Color.White
                    )
                ) {
                    Text("Debug", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
