package com.example.login.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Register(onclick : () -> Unit){
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
            placeholder = { Text(text = "confirmpassword") },
            leadingIcon = {
                Image(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "confirmpassword"
                )
            },
        )
        Button(onClick = { onclick() }) {
            Text(text = "register")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Meet(){
    Register (onclick = {})
}