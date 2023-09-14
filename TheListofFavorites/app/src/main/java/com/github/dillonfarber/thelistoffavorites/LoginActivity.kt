package com.github.dillonfarber.thelistoffavorites

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.datastore.dataStore
import androidx.lifecycle.Observer
import com.github.dillonfarber.thelistoffavorites.APIs.IGDBAutho
import com.github.dillonfarber.thelistoffavorites.DataClasses.Request.BearerTokenResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val Context.dataStore by dataStore(R.raw.bearerkeys.toString(), BearerTokenSerializer)

class LoginActivity : AppCompatActivity() {
    private var token = BearerTokenResponse()
    private var tokenFlag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn: Button = findViewById(R.id.loginBtn)

        IGDBAutho.bearerToken.observe(this, Observer {
            Log.i("Token", it.toString())
            token = it
            CoroutineScope(Dispatchers.IO).launch {
                update(it)
            }
        })


        loginBtn.setOnClickListener{
            val intent = Intent(this, MainListView::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
    }
    suspend fun update(token: BearerTokenResponse){
        dataStore.updateData {
            it.copy(
                accessToken = token.accessToken,
                tokenType = token.tokenType,
                expiresIn = token.expiresIn
            )
        }
    }
    init {
        if(!tokenFlag) {
            IGDBAutho.getBearerToken()
            tokenFlag = true
        }

    }

}

