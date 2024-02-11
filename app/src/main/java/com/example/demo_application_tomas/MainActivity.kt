package com.example.demo_application_tomas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.demo_application_tomas.ui.theme.Demo_Application_TomasTheme
import com.example.demo_application_tomas.ui.theme.views.LoggedInView
import com.example.demo_application_tomas.ui.theme.views.ScanView


class MainActivity : ComponentActivity() {

    val DEFAULT_PASSWORD = "TOMAS"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QrApp() // <--- Root UI componentas
        }
    }

    @Composable
    fun QrApp() {
        var password by remember { mutableStateOf("") }   // <---- remeber raktazodis sutriggerina ,kad persipiestu viewsai
        Demo_Application_TomasTheme {
            if (password == DEFAULT_PASSWORD) { // <--- Check ar atitinka passwordas
                LoggedInView()   // jeigu true renderinam prisiloginusio viewsa
            } else {
                ScanView() { newPassword ->   // registruojam callback funkcija
                    password = newPassword
                }
            }
        }
    }
}
