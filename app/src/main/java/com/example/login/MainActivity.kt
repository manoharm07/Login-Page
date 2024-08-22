package com.example.login

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.login.ui.theme.LoginTheme
import com.example.login.Screen.Login
import com.example.login.Screen.Register
import com.example.login.Screen.Content
import com.example.login.room.LoginViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginTheme {
                Surface {
                    App()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(){
    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = "log") {
        composable(route = "log"){
            Login(viewModel = LoginViewModel(), navController)
        }
        composable("reg"){
            Register(viewModel = LoginViewModel()){
                navController.navigate("log")
            }
        }
        composable("content/{uname}", arguments = listOf(
            navArgument("uname"){
                type = NavType.StringType
            }
        )){
            val username = it.arguments!!.getString("uname")
            Content(username!!)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun Greet(){
    App()
}
