package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.Controller.LoginController
import com.example.myapplication.Database.DatabaseHelper
import com.example.myapplication.Interface.Authentication
import com.example.myapplication.Model.User
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Authentication {

    private lateinit var binding : ActivityMainBinding
    private lateinit var UsernameET : EditText
    private lateinit var PasswordET : EditText
    private lateinit var signinBtn : Button
    private lateinit var signupTv : TextView
    private lateinit var username : String
    private lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        UsernameET = binding.usernameET
        PasswordET = binding.passwordET
        signinBtn = binding.signIn
        signupTv = binding.signUp

        signinBtn.setOnClickListener{
            username = UsernameET.text.toString();
            password = PasswordET.text.toString();

            val loginController : LoginController = LoginController(this@MainActivity)
            loginController.validateLogin(username,password)
        }

        signupTv.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            finishAndRemoveTask()
            startActivity(intent)
        }

    }



    override fun onError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        Toast.makeText(this, "Succesfully Login", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("username", username)
        intent.putExtra("password", password)
        finishAndRemoveTask()
        startActivity(intent)
    }

    override fun insertUser(newUser: User) {
        TODO("Not Implemented")
    }

    override fun GetUserData(username : String, password : String) : User?{
        val databaseHelper = DatabaseHelper(this)
        return databaseHelper.getCurrUser(username,password)
    }
}