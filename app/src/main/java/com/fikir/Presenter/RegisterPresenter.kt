package com.fikir.Presenter

import android.util.Log
import com.fikir.Model.Firebase.RegisterAcc
import com.fikir.UI.Activities.RegisterActivity

class RegisterPresenter {
    lateinit var registerActivity: RegisterActivity
    fun init(registerActivity: RegisterActivity){
        this.registerActivity=registerActivity
    }
    fun register(nickname:String,email:String,pass1:String,pass2:String){
        email.trim()
        Log.d("gelen1",email)
        Log.d("gelen2",pass1)
        Log.d("gelen3",pass2)
        if(email.length==0) {
            registerActivity.emptymail()
        }
        else if(pass2.length==0){
            registerActivity.emptypass2()
        }
        else if(pass1.length==0) {
                registerActivity.emptypass1()
        }
        else if(pass1!=pass2){
            registerActivity.passdontmatch()
        }
        else if(pass1.length<8 && pass2.length<8){
            registerActivity.passshort()
        }
        else if(nickname.length==0){
            registerActivity.emptynickname()
        }
        else if(nickname.length<8){
            registerActivity.nicknameshort()
        }
        else{
            Log.d("gelenler","oldu")
            val RegisterAcc=RegisterAcc()
            RegisterAcc.register(nickname,email,pass1)
    }
    }
}