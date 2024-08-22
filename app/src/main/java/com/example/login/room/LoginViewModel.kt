package com.example.login.room

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val user = Main.login_db.getuserdao()

    fun getuserlist() : List<data>{
        return user.getuserinfo()
    }

//    fun checkUser(username: String, password: String, callback : (Boolean) -> Unit){
//        viewModelScope.launch(Dispatchers.IO) {
//            val list = getuserlist()
//           val userfound = list.any { it.username == username && it.password == password }
//            callback(userfound)
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addinfo(username: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            user.adduser(data(username = username, mail = email, password = password))
        }
    }
}