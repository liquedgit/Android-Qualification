package com.example.myapplication.Interface

import com.example.myapplication.Model.User

interface Authentication {
    fun onError(errorMessage : String)
    fun onSuccess()
    fun insertUser(newUser : User)
    fun GetUserData(username : String, password : String) : User?
}