package com.example.contact_app_instant_task.uicomposables

import android.content.Intent
import android.net.Uri
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contact_app_instant_task.R
import com.example.contact_app_instant_task.data.Person
import com.example.contact_app_instant_task.model.PersonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardView(person:Person,
             name:String,
             number:String,
             viewModel: PersonViewModel= androidx.lifecycle.viewmodel.compose.viewModel()){

    var sheetState = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(false) }
    val context=LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    var showConfirmationDialog by remember { mutableStateOf(false) }

    if (isSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isSheetOpen = false },
            sheetState = sheetState,
            dragHandle = { },
        ) {
            MoreInPerson(
                onDismiss = { isSheetOpen = false }
                ,viewModel = viewModel
                ,id = person.id
                ,name = person.name
                ,number = person.number
                , data2 = Person(0,name,number)
            )
        }
    }
    // AlertDialog to show when person.number is empty
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("No Phone Number", color = Color.Red) },
            text = { Text("< ${person.name} > not have a phone number.") },
            confirmButton = {
                Row ( modifier = Modifier.padding(end = 70.dp), // Add padding around the box
                    verticalAlignment = Alignment.CenterVertically){
                Button(modifier = Modifier
                    ,onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(Color.Red)) {
                    Text("Close")
                }
            }
            }
        )
    }


    if (showConfirmationDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmationDialog = false },
            title = { Text("Confirm Call") },
            text = { Text("Do you want to call ${person.number}?") },
            confirmButton = {
                Row ( modifier = Modifier.padding(end = 15.dp), // Add padding around the box
                    verticalAlignment = Alignment.CenterVertically){
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${person.number}")
                        }
                        context.startActivity(intent)
                        showConfirmationDialog = false
                    }, colors = ButtonDefaults.buttonColors(Color.Green)) {
                        Text("Yes")
                    }
                }

            },
            dismissButton = {
                Row ( modifier = Modifier
                    .padding(end = 50.dp)
                , verticalAlignment = Alignment.CenterVertically){
                Button(onClick = { showConfirmationDialog = false },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                , modifier = Modifier) {
                    Text("No")
                }
            }
            }
        )
    }
    ElevatedCard(
        onClick = {if (person.number.isNotEmpty())
        { showConfirmationDialog=true }else{
            showDialog=true
            } },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(Color.White)
        , elevation = CardDefaults.cardElevation(8.dp)
        , colors = CardDefaults.cardColors()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f), // Make the Column flexible
            ) {
                // Name Text
                Text(
                    text = formatTextForNewLineAfterTwoWords(person.name),
                    color = Color.DarkGray,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    modifier = Modifier.wrapContentSize()
                )
                Spacer(modifier = Modifier.padding(5.dp))
                // Number Text
                SelectionContainer {
                    Text(
                        text = person.number,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.DarkGray,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier
                    )
                }

            }

            // More icon (Image) on the right side, stays visible
            Row(
                modifier = Modifier
                    .width(70.dp)
                    .clickable { isSheetOpen = true }
                    .align(Alignment.CenterVertically) // Align the image vertically in the Row
            ) {
                Image(
                    painter = painterResource(id = R.drawable.moreicon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .size(40.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}


@Preview
@Composable
fun CardViewPreview(){
    CardView(person = Person(id = 0,name = "", number = ""), name = "", number = "" )
}



fun formatTextForNewLineAfterTwoWords(input: String): String {
    val words = input.split(" ")
    val stringBuilder = StringBuilder()

    for (i in words.indices) {
        stringBuilder.append(words[i])
        if ((i + 1) % 3 == 0) {
            stringBuilder.append("\n") // Insert a new line after two words
        } else {
            stringBuilder.append(" ")  // Add space between words
        }
    }

    return stringBuilder.toString().trim()
}
