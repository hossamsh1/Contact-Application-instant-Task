package com.example.contact_app_instant_task.screens

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contact_app_instant_task.R
import com.example.contact_app_instant_task.data.Person
import com.example.contact_app_instant_task.model.PersonViewModel
import com.example.contact_app_instant_task.uicomposables.AddNewPerson
import com.example.contact_app_instant_task.uicomposables.CardView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(data:Person
    ,viewModel: PersonViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(false) }
    val persons by viewModel.getAllPerson().collectAsState(initial = emptyList())
    val context = LocalContext.current

    if (isSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isSheetOpen = false },
            sheetState = sheetState,
            dragHandle = { },
        ) {
            AddNewPerson(
                onDismiss = { isSheetOpen = false }
            )
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {



        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp , bottom = 30.dp)) {
            if (persons.isNotEmpty()) {
                items(persons) { person ->
                    CardView(
                        person = person,
                        viewModel = viewModel,
                        name = person.name
                        , number =  person.number
                    )
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.addicon),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 10.dp, end = 10.dp)
                .clickable { isSheetOpen = true }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(viewModel = viewModel(), data = Person(id = 0, name = "", number = ""))
}
