package com.fikir.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.fikir.Model.Firebase.Status
import com.fikir.Presenter.LoginPresenter
import com.fikir.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val presenter:LoginPresenter= LoginPresenter()
        presenter.init(this)
        findViewById<TextView>(R.id.kayitol).setOnClickListener { presenter.kayitol() }
        findViewById<Button>(R.id.loginbutton).setOnClickListener { presenter.login(
            findViewById<TextInputEditText>(R.id.loginmailtext).text.toString(),
            findViewById<TextInputEditText>(R.id.loginpasstext).text.toString()
        ) }
    }
    fun emptymail(){
        findViewById<TextInputLayout>(R.id.loginmaillay).error="Bos bırakma"
    }

    fun emptypass(){
        findViewById<TextInputLayout>(R.id.loginpasslay).error="Bos bırakma"
    }

    fun shortpass(){
        findViewById<TextInputLayout>(R.id.loginpasslay).error="Sifre en az 8 karakterden olusmalı"
    }

    fun shortmail(){
        findViewById<TextInputLayout>(R.id.loginmaillay).error="Mail en az 8 karakterden olusmalı"
    }
    fun loginerror(){
        findViewById<TextInputLayout>(R.id.loginpasslay).error="Sifreniz yanlis olabilir"
        findViewById<TextInputLayout>(R.id.loginmaillay).error="Mailiniz yanlis olabilir"
    }
}
