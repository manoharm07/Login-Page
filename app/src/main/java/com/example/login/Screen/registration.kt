package com.example.login.Screen

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login.room.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Register(viewModel: LoginViewModel,onclick : () -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        var username by remember {
            mutableStateOf("")
        }
        val context = LocalContext.current
        var password by remember {
            mutableStateOf("")
        }
        var confirmpassword by remember {
            mutableStateOf("")
        }
        var email by remember{
            mutableStateOf("")
        }
        OutlinedTextField(
            value = username,
            onValueChange = { username = it},
            placeholder = { Text(text = "username") },
            leadingIcon = {
                Image(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "user"
                )
            },
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it},
            keyboardActions = KeyboardActions.Default,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = { Text(text = "email") },
            leadingIcon = {
                Image(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "email"
                )
            },
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it},
            keyboardActions = KeyboardActions.Default,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            placeholder = { Text(text = "password") },
            leadingIcon = {
                Image(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "password"
                )
            },
        )
        OutlinedTextField(
            value = confirmpassword,
            onValueChange = { confirmpassword = it},
            keyboardActions = KeyboardActions.Default,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            placeholder = { Text(text = "confirmpassword") },
            leadingIcon = {
                Image(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "confirmpassword"
                )
            },
        )
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                var found: Boolean = false
                val users = viewModel.getuserlist()

                found = users.any { it.username == username && it.password == password }

                // Switch back to the Main thread to handle UI updates
                withContext(Dispatchers.Main) {

                    // Check for the password correctness
                    if(password != confirmpassword){
                        val toast = Toast.makeText(context, "Password doesnot match", Toast.LENGTH_SHORT)
                        toast.setGravity(android.view.Gravity.RELATIVE_LAYOUT_DIRECTION, 0, 0)
                        toast.show()
                        return@withContext
                    }
                    // Check if any field is empty
                    if (username.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty()) {
                        val toast = Toast.makeText(context, "Please fill all the details!!", Toast.LENGTH_SHORT)
                        toast.setGravity(android.view.Gravity.RELATIVE_LAYOUT_DIRECTION, 0, 0)
                        toast.show()
                        return@withContext // Exit if any field is empty
                    }

                    // Check if user already exists
                    if (found) {
                        val toast = Toast.makeText(context, "UserName Already exists!!", Toast.LENGTH_SHORT)
                        toast.setGravity(android.view.Gravity.RELATIVE_LAYOUT_DIRECTION, 0, 0)
                        toast.show()
                        return@withContext // Exit if user exists
                    }

                    // If everything is valid, perform the registration
                    onclick()
                    val toast = Toast.makeText(context, "Registration Successful!!", Toast.LENGTH_SHORT)
                    toast.setGravity(android.view.Gravity.RELATIVE_LAYOUT_DIRECTION, 0, 0)
                    toast.show()

                    // Add user info to the database or data source
                    viewModel.addinfo(username = username, email = email, password = password)
                }
            }
        }) {
            Text(text = "register")
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun Meet(){
    Register (viewModel = LoginViewModel(), onclick = {})
}