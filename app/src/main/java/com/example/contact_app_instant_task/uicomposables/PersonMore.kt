package com.example.contact_app_instant_task.uicomposables

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
fun MoreInPerson(onDismiss: () -> Unit
                 ,viewModel: PersonViewModel= androidx.lifecycle.viewmodel.compose.viewModel()
                 ,id:Int
                 ,name:String
                 ,number:String
                 ,data2:Person
) {

    var data4 by remember { mutableStateOf(name) }
    var data1 by remember { mutableStateOf(number) }
    val context = LocalContext.current

    Column(modifier = Modifier.background(Color.White)) {

        OutlinedTextField(
            value = data4,
            onValueChange = { data4=it },
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
            value = data1,
            onValueChange = { data1=it },
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
Row (modifier = Modifier.align(Alignment.CenterHorizontally))
{
    Button(onClick = {try {
        viewModel.upsertPerson(Person(id,data4,data1))
        Toast.makeText(context, "Person Updated", Toast.LENGTH_SHORT).show()
        onDismiss()
    } catch (e:Exception){
        Toast.makeText(context, "Person not updated", Toast.LENGTH_SHORT).show()
    } }
        , colors = ButtonDefaults.buttonColors(Color.Gray)
        , shape = RoundedCornerShape(10.dp)
        , modifier = Modifier
            .padding(top = 20.dp, bottom = 20.dp)
            .wrapContentSize()
    ) {
        Text(
            text = "Update",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
Spacer(modifier = Modifier.padding(start = 30.dp))
    Button(onClick = {try {
        viewModel.deletePerson(Person(id,data4,data1))
        Toast.makeText(context, "Person Updated", Toast.LENGTH_SHORT).show()
        onDismiss()
    } catch (e:Exception){
        Toast.makeText(context, "Person not updated", Toast.LENGTH_SHORT).show()
    } }
        , colors = ButtonDefaults.buttonColors(Color.Gray)
        , shape = RoundedCornerShape(10.dp)
        , modifier = Modifier
            .padding(top = 20.dp, bottom = 20.dp)
            .wrapContentSize()
    ) {
        Text(
            text = "Delete",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

Spacer(modifier = Modifier.padding(top = 25.dp))

Row (modifier = Modifier
    .fillMaxWidth()
    .padding(start = 10.dp, end = 10.dp, bottom = 60.dp)){

    CardWithImage(click = {
        val  smsBody = "Hello ${data2.name}" // Replace with your desired SMS body
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("smsto:${data2.number}") // Set the URI for the SMS
                putExtra("sms_body", smsBody) // Add the SMS body
            }
            context.startActivity(intent) // Start the activity
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "No messaging app found", Toast.LENGTH_SHORT).show() // Show a message if no messaging app is found
        }
    }, image = R.drawable.smsicon)

Spacer(modifier = Modifier.padding(2.dp))

    CardWithImage(click = {
        val whatsappMessage = "Hello, this is a message via WhatsApp!"

        try {
            val whatsappIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://api.whatsapp.com/send?phone=${data2.number}&text=${Uri.encode(whatsappMessage)}")
            }
            context.startActivity(whatsappIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "WhatsApp is not installed", Toast.LENGTH_SHORT).show()
        }
    }, image = R.drawable.watsappicon)

Spacer(modifier = Modifier.padding(2.dp))

    CardWithImage(click = {
        val telegramMessage = "Hello, this is a message via Telegram!"

        try {
            val telegramIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("tg://msg?text=${Uri.encode(telegramMessage)}&to=${data2.number}")
            }
            context.startActivity(telegramIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Telegram is not installed", Toast.LENGTH_SHORT).show()
        }
    }, image = R.drawable.telegramicon)

}

    }
}

@Preview(showBackground = true)
@Composable
fun morePreview() {
   // MoreInPerson {/*TODO*/ }
}

@Composable
fun CardWithImage(click:() -> Unit,image:Int){
    ElevatedCard(
        onClick = click ,
        modifier = Modifier
            .padding(16.dp)
        , elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Image(painter = painterResource(image)
            , contentDescription =null
            , modifier = Modifier
                .padding(8.dp)
                .size(60.dp))
    }
}


fun openApp(context: Context, packageName: String) {
    val intent = context.packageManager.getLaunchIntentForPackage(packageName)
    if (intent != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "App not found", Toast.LENGTH_SHORT).show()
    }
}