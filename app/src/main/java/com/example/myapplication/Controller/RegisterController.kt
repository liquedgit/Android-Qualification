package com.example.myapplication.Controller

import android.widget.Toast
import com.example.myapplication.Database.DatabaseHelper
import com.example.myapplication.Interface.Authentication
import com.example.myapplication.Model.User

class RegisterController (private val authentication : Authentication) {
    private lateinit var databaseHelper: DatabaseHelper
    fun handleSignUp(username: String, password: String, confirmPassword : String){
        if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            authentication.onError("Please fill in all the fields")
            return
        }else if(!password.equals(confirmPassword)){
            authentication.onError("Password not match")
            return
        }else{
            val newUser = User(username, password, 0)

            authentication.insertUser(newUser)
            authentication.onSuccess()

        }
    }

}