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

        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)

        loginBtn.setOnClickListener{

            Toast.makeText(this@MainActivity,ai.metaData["KEY"].toString(), Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainListView::class.java)
            startActivity(intent)
        }

    }
}
