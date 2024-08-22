package com.example.login.Screen


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login.room.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Login(viewModel: LoginViewModel, navController: NavController){
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
        var password by remember {
            mutableStateOf("")
        }

        var nouser by remember {
            mutableStateOf(true)
        }

        Text(
            text = "Login",
            fontSize = 36.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF4A5DC9)
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            keyboardActions = KeyboardActions.Default,
            placeholder = { Text(text = "username") },
            leadingIcon = {
                Image(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "user"
                )
            },
            isError = !nouser
        )

        var noinp by remember {
            mutableStateOf(true)
        }

        var passwordvisibility by remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = { Text(text = "password") },
            leadingIcon = {
                Image(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = ""
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                Image(
                    imageVector = if (passwordvisibility) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            passwordvisibility = !passwordvisibility
                        }
                )
            },
            visualTransformation = if (passwordvisibility) VisualTransformation.None else PasswordVisualTransformation()

        )
        if (!nouser) {
            Text(text = "Wrong username or password", fontSize = 16.sp, color = Color(0xFFE46969))
        }
        if (!noinp) {
            Text(text = "Please enter username and password", fontSize = 16.sp, color = Color(0xFFE46969))
        }

        Button(onClick = {
            if (username != "" && password != "") {
                CoroutineScope(Dispatchers.IO).launch {
                    val users = viewModel.getuserlist()
                    val found = users.any { it.username == username && it.password == password }

                    withContext(Dispatchers.Main) {
                        if (found) {
                            navController.navigate("content/$username")
                        } else {
                            nouser = false
                            noinp = true
                        }
                    }
                }
            }
            else{
                noinp = false
                nouser = true
            }
        }) {
            Text(text = "login")
        }

        Row {
            Text(text = "Don't have an account?", fontSize = 16.sp)
            Text(text = "Create one",
                fontSize = 16.sp,
                color = Color.Blue,
                modifier = Modifier.clickable{
                    navController.navigate("reg")
                })
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun Greet(){
    Login(viewModel = LoginViewModel(),navController = rememberNavController())
}