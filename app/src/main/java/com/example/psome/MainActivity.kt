package com.example.psome

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    private val requestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity","App opened")

        if (!isPermissionGranted()) {
            askPermissions()
            Log.d("MainActivity","Permissions asked")
        }

        auth = Firebase.auth
    }

    private fun askPermissions() {
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }

    private fun isPermissionGranted(): Boolean {
        permissions.forEach {
            if (ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        currentUser?.let {
            Log.d("testas","i")
        }

        val localPrefs = "MyPrefsFile"
        val settings = getSharedPreferences(localPrefs, 0)

        when {
            currentUser != null -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            settings.getBoolean("my_first_time", true) -> {
                settings.edit().putBoolean("my_first_time", false).apply()
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            else -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        Firebase.auth.signOut()
        super.onDestroy()
    }
}