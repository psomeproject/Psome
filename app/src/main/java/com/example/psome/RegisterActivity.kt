package com.example.psome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Log.d("RegisterActivityLog","ActivityStarted")
    }
}