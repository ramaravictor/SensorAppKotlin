package com.example.sensorapp.presentation.debug

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Environment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.opencsv.CSVWriter
import kotlinx.serialization.Serializable
import java.io.File
import java.io.FileWriter

@Serializable
object RecordRoute

@Composable
fun RecordScreen(
    navController: NavController
) {
    var isAccelerometerChecked by remember { mutableStateOf(true) }
    var isStepCounterChecked by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    val sensorData = remember { mutableListOf<Array<String>>() }

    val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event != null) {
                if (event.sensor.type == Sensor.TYPE_ACCELEROMETER && isAccelerometerChecked) {
                    sensorData.add(
                        arrayOf(
                            "Accelerometer",
                            event.values[0].toString(),
                            event.values[1].toString(),
                            event.values[2].toString()
                        )
                    )
                } else if (event.sensor.type == Sensor.TYPE_STEP_COUNTER && isStepCounterChecked) {
                    sensorData.add(
                        arrayOf(
                            "Step Counter",
                            event.values[0].toString()
                        )
                    )
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }


    fun startRecording() {
        if (isAccelerometerChecked) {
            sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        if (isStepCounterChecked) {
            sensorManager.registerListener(sensorEventListener, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stopAndExportData() {
        sensorManager.unregisterListener(sensorEventListener)

        val file = File(Environment.getExternalStorageDirectory(), "sensor_data.csv")
        val csvWriter = CSVWriter(FileWriter(file))

        // Header
        csvWriter.writeNext(arrayOf("Sensor Type", "Value X", "Value Y", "Value Z"))

        // Data
        for (data in sensorData) {
            csvWriter.writeNext(data)
        }

        csvWriter.close()
    }

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF6F6F6)), // Background color
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Debug Activity",
                    fontSize = 28.sp,
                    color = Color(0xFF3F51B5),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Checkboxes for sensors
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isAccelerometerChecked,
                        onCheckedChange = { isAccelerometerChecked = it }
                    )
                    Text(
                        text = "Accelerometer",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isStepCounterChecked,
                        onCheckedChange = { isStepCounterChecked = it }
                    )
                    Text(
                        text = "Step Counter",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Start Button
                Button(
                    onClick = { startRecording() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3F51B5),
                        contentColor = Color.White
                    )
                ) {
                    Text("Start Recording", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Stop and Export Button
                Button(
                    onClick = { stopAndExportData() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD32F2F),
                        contentColor = Color.White
                    )
                ) {
                    Text("Stop & Export", fontSize = 16.sp)
                }
            }
        }
    }
}
