package com.fikir.Presenter

import android.content.Intent
import android.util.Log
import com.fikir.Model.Firebase.LoginAcc
import com.fikir.UI.Activities.Login
import com.fikir.UI.Activities.Main
import com.fikir.UI.Activities.Register
import kotlin.math.log

class LoginPresenter {
    var login: Login = Login()
    fun init(login: Login){
        this.login=login
    }
    fun kayitol(){
        login.startActivity(Intent(login.applicationContext, Register::class.java))
    }
    fun login(mail:String,password:String){
        if(mail.length==0){
            login.emptymail()
        }
        else if(password.length==0){
            login.emptypass()
        }
        else if(password.length<8){
            login.shortpass()
        }
        else if(mail.length<8){
            login.shortmail()
        }
        else{
            LoginAcc().login(mail,password,login)
        }
    }

    fun logincomplete(){
        var intent:Intent=Intent(login.applicationContext,Main::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        login?.startActivity(intent)
        //login?.applicationContext?.startActivity(Intent(login, Main::class.java))
        Log.d("gelenlogin","girdi")
    }

    fun loginfail(){
        login.loginerror()
        Log.d("gelenlogin","olmadı")
    }
}