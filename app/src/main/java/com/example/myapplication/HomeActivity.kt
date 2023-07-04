package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var WelcomeMessageTv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WelcomeMessageTv = binding.welcomeMessage

        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        WelcomeMessageTv.text = "Welcome, " + username + "With Password "+ password
    }
}