package com.github.dillonfarber.thelistoffavorites

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBtn: Button = findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener{
            val intent = Intent(this, MainListView::class.java)
            startActivity(intent)
        }
    }
}