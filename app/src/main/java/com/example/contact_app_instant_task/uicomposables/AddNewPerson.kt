package com.example.contact_app_instant_task.uicomposables

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contact_app_instant_task.R
import com.example.contact_app_instant_task.data.Person
import com.example.contact_app_instant_task.model.PersonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewPerson(onDismiss: () -> Unit
                 ,viewModel: PersonViewModel= androidx.lifecycle.viewmodel.compose.viewModel()) {

    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.background(Color.White)) {

        OutlinedTextField(
            value = name,
            onValueChange = { name=it },
            placeholder = {
                Text(
                    text = "Add New Name",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.nameicon), // Use ImageVector.vectorResource
                    contentDescription = null
                    ,modifier = Modifier
                        .align(Alignment.End)
                        .size(25.dp)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 25.dp, end = 25.dp, top = 20.dp),
            shape = RoundedCornerShape(8.dp)
            ,colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = Color.Gray
            )
        )
Spacer(modifier = Modifier.padding(3.dp))
        OutlinedTextField(
            value = number,
            onValueChange = { number=it },
            placeholder = {
                Text(
                    text = "Add New Number",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.callicon), // Use ImageVector.vectorResource
                    contentDescription = null
                    ,modifier = Modifier
                        .align(Alignment.End)
                        .size(25.dp)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 25.dp, end = 25.dp, top = 20.dp),
            shape = RoundedCornerShape(8.dp)
            ,colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Gray,
                focusedBorderColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.padding(3.dp))

        Button(
            onClick = { viewModel.upsertPerson(Person(name = name, number = number))
                Toast.makeText(context, "Person Added", Toast.LENGTH_SHORT).show()
            onDismiss() }
            , colors = ButtonDefaults.buttonColors(Color.Gray)
            , shape = RoundedCornerShape(10.dp)
            , modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp, bottom = 20.dp)
                .wrapContentSize()
        ) {
            Text(
                text = "Add new task",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
   AddNewPerson(onDismiss = { /*TODO*/ })
}