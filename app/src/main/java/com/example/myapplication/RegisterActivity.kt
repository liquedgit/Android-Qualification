package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.Controller.RegisterController
import com.example.myapplication.Database.DatabaseHelper
import com.example.myapplication.Interface.Authentication
import com.example.myapplication.Model.User
import com.example.myapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), Authentication {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var usernameEt : EditText
    private lateinit var passwordEt : EditText
    private lateinit var confPasswordEt : EditText
    private lateinit var signupBtn : Button
    private lateinit var loginTv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usernameEt = binding.usernameET
        passwordEt = binding.passwordET
        confPasswordEt = binding.confirmPassword
        signupBtn = binding.signUp
        loginTv = binding.alreadyHaveAccount


        signupBtn.setOnClickListener{
            val registerController = RegisterController(this@RegisterActivity)
            registerController.handleSignUp(usernameEt.text.toString(), passwordEt.text.toString(), confPasswordEt.text.toString())
        }
    }

    override fun onError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        Toast.makeText(this, "Succesfully registered new Account", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        finishAndRemoveTask()
        startActivity(intent)
    }

    override fun insertUser(newUser: User) {
        val databaseHelper : DatabaseHelper = DatabaseHelper(this)
        databaseHelper.InsertNewUser(newUser)
    }

    override fun GetUserData(username: String, password: String): User? {
        TODO("Not implemented")
    }
}