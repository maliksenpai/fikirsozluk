package com.fikir.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.fikir.Presenter.RegisterPresenter
import com.fikir.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val presenter=RegisterPresenter()
        presenter.init(this)
        findViewById<Button>(R.id.registerbutton).setOnClickListener {
            presenter.register(findViewById<TextInputEditText>(R.id.registernicktext).text.toString(),
                findViewById<TextInputEditText>(R.id.registermailtext).text.toString(),
                findViewById<TextInputEditText>(R.id.registerpass1text).text.toString(),
                findViewById<TextInputEditText>(R.id.registerpass2text).text.toString()) }
    }
    fun passdontmatch(){
        findViewById<TextInputLayout>(R.id.registerpass1layout).error="Sifre uyusmuyor"
        findViewById<TextInputLayout>(R.id.registerpass2layout).error="Sifre uyusmuyor"
    }
    fun passshort(){
        findViewById<TextInputLayout>(R.id.registerpass1layout).error="Sifre 8 karakterden fazla olmalı"
        findViewById<TextInputLayout>(R.id.registerpass2layout).error="Sifre 8 karakterden fazla olmalı"
    }
    fun nicknameshort(){
        findViewById<TextInputLayout>(R.id.registernicklay).error="Kullanıcı adı en az 8 karakterden olusmalı"
    }
    fun emptypass1(){
        findViewById<TextInputLayout>(R.id.registerpass1layout).error="Bos bırakma"
    }
    fun emptypass2(){
        findViewById<TextInputLayout>(R.id.registerpass2layout).error="Bos bırakma"
    }
    fun emptynickname(){
        findViewById<TextInputLayout>(R.id.registernicklay).error="Bos bırakma"
    }
    fun emptymail(){
        findViewById<TextInputLayout>(R.id.registermaillayout).error="Bos bırakma"
    }
    fun registersuccess(){
        Toast.makeText(this,"Kayıt Basarılır",Toast.LENGTH_LONG)
    }
    fun registerfail(){
        Toast.makeText(this,"Hata",Toast.LENGTH_LONG)
    }
}
