/*
 * SPDX-FileCopyrightText: 2023 microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 */

package org.microg.vending.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.vending.R

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
        private const val GMS_PACKAGE_NAME = "com.google.android.gms"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen(
                        onLaunchClick = { launchMicroG() }
                    )
                }
            }
        }
    }

    private fun launchMicroG() {
        try {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.setPackage(GMS_PACKAGE_NAME)

            try {
                startActivity(intent)
            } catch (e: Exception) {
                Log.w(TAG, "MAIN activity is not DEFAULT. Trying to resolve instead.")

                packageManager.resolveActivity(intent, 0)?.let { resolved ->
                    intent.setClassName(
                        GMS_PACKAGE_NAME,
                        resolved.activityInfo.name
                    )
                    startActivity(intent)
                } ?: run {
                    Toast.makeText(
                        this,
                        getString(R.string.toast_not_installed),
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
            }

            Toast.makeText(
                this,
                getString(R.string.toast_installed),
                Toast.LENGTH_LONG
            ).show()

        } catch (e: Exception) {
            Log.w(TAG, "Failed launching microG Settings", e)

            Toast.makeText(
                this,
                getString(R.string.toast_not_installed),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

@Composable
fun MainScreen(onLaunchClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "microG Vending",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Abrir servicios de microG",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onLaunchClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Abrir microG")
        }
    }
}