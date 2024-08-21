package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

class MainActivity : ComponentActivity() {
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

@Composable
fun App(){
    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = "log") {
        composable(route = "log"){
            Login(navController)
        }
        composable("reg"){
            Register{
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

@Preview(showSystemUi = true)
@Composable
fun Greet(){
    App()
}
