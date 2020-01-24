package com.fikir.Presenter

import android.content.Intent
import android.util.Log
import com.fikir.Model.Firebase.RegisterAcc
import com.fikir.UI.Activities.Main
import com.fikir.UI.Activities.Register

class RegisterPresenter {
    lateinit var register: Register
    fun init(register: Register){
        this.register=register
    }
    fun register(nickname:String,email:String,pass1:String,pass2:String){
        email.trim()
        Log.d("gelen1",email)
        Log.d("gelen2",pass1)
        Log.d("gelen3",pass2)
        if(email.length==0) {
            register.emptymail()
        }
        else if(pass2.length==0){
            register.emptypass2()
        }
        else if(pass1.length==0) {
                register.emptypass1()
        }
        else if(pass1!=pass2){
            register.passdontmatch()
        }
        else if(pass1.length<8 && pass2.length<8){
            register.passshort()
        }
        else if(nickname.length==0){
            register.emptynickname()
        }
        else if(nickname.length<8){
            register.nicknameshort()
        }
        else{
            Log.d("gelenler","oldu")
            val RegisterAcc=RegisterAcc()
            RegisterAcc.register(nickname,email,pass1)
    }
    }
}