package com.fikir.Presenter

import android.content.Intent
import android.util.Log
import com.fikir.Model.Firebase.LoginAcc
import com.fikir.UI.Activities.LoginActivity
import com.fikir.UI.Activities.MainActivity
import com.fikir.UI.Activities.RegisterActivity

class LoginPresenter {
    var loginActivity: LoginActivity = LoginActivity()
    fun init(loginActivity: LoginActivity){
        this.loginActivity=loginActivity
    }
    fun kayitol(){
        loginActivity.startActivity(Intent(loginActivity.applicationContext, RegisterActivity::class.java))
    }
    fun login(mail:String,password:String){
        if(mail.length==0){
            loginActivity.emptymail()
        }
        else if(password.length==0){
            loginActivity.emptypass()
        }
        else if(password.length<8){
            loginActivity.shortpass()
        }
        else if(mail.length<8){
            loginActivity.shortmail()
        }
        else{
            LoginAcc().login(mail,password,loginActivity)
        }
    }

    fun logincomplete(){
        var intent:Intent=Intent(loginActivity.applicationContext,MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        loginActivity?.startActivity(intent)
        //loginActivity?.applicationContext?.startActivity(Intent(loginActivity, MainActivity::class.java))
        Log.d("gelenlogin","girdi")
    }

    fun loginfail(){
        loginActivity.loginerror()
        Log.d("gelenlogin","olmadı")
    }
}