package com.example.login.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun Content(username : String, email : String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF697DEC))
            .wrapContentSize()

    ) {
        Text(
            text = "Welcome $username \nYour email is : $email",
            fontSize = 36.sp,
            color = Color.Blue
            )
    }
}