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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var data1 by remember { mutableStateOf(data.name) }


    // Filter the list of persons based on the search query
    val filteredPersons = if (data1.isEmpty()) {
        persons
    } else {
        persons.filter { person ->
            person.name.contains(data1, ignoreCase = true)
        }
    }

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
            .padding(top = 70.dp, bottom = 30.dp)) {
            if (filteredPersons.isNotEmpty()) {
                items(filteredPersons) { person ->
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

Card(modifier = Modifier
    .alpha(0.8f)
    .padding(start = 15.dp, end = 15.dp, top = 35.dp)
,shape = RoundedCornerShape(30.dp)) {
    OutlinedTextField(
        value = data1,
        onValueChange = { data1 = it },
        placeholder = {
            Text(
                text = "Search",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        },
        trailingIcon = {
            Image(
                painter = painterResource(id = R.drawable.searshicon), // Use ImageVector.vectorResource
                contentDescription = null, modifier = Modifier
                    .align(Alignment.End)
                    .size(30.dp)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        textStyle = TextStyle(Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 0.dp, end = 0.dp, top = 0.dp),
        shape = RoundedCornerShape(30.dp), colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.White,
            focusedBorderColor = Color.DarkGray
        )
    )
}
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(viewModel = viewModel(), data = Person(id = 0, name = "", number = ""))
}
