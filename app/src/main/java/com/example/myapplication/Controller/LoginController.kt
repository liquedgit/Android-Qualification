package com.example.myapplication.Controller

import com.example.myapplication.Interface.Authentication
import com.example.myapplication.Model.User

class LoginController (private val authentication: Authentication) {
    fun validateLogin(username : String, password: String){
        if(username.isEmpty() || password.isEmpty())authentication.onError("Please fill in all the fields")
        else{
            //check from db
            val user : User? = authentication.GetUserData(username, password)
            if(user != null){
                authentication.onSuccess();
            }else{
                authentication.onError("Login failed")
            }
        }
    }
}