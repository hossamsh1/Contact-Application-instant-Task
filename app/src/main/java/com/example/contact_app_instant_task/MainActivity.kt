package com.example.contact_app_instant_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.contact_app_instant_task.data.Person
import com.example.contact_app_instant_task.model.PersonViewModel
import com.example.contact_app_instant_task.screens.HomeScreen
import com.example.contact_app_instant_task.ui.theme.Contact_app_instant_taskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
HomeScreen( data = Person(id = 0, name = "", number = ""))
        }
    }
}
