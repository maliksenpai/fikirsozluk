package com.fikir.Model.Firebase

import android.content.Intent
import android.util.Log
import com.fikir.Model.Singletons.AuthSingleton
import com.fikir.Presenter.LoginPresenter
import com.fikir.UI.Activities.Login
import com.google.firebase.auth.FirebaseAuth

class LoginAcc {
    fun login(mail:String,password:String,login: Login){
        var auth:FirebaseAuth?=AuthSingleton().getInstance()
        var presenter:LoginPresenter=LoginPresenter()
        presenter.init(login)
        auth?.signInWithEmailAndPassword(mail,password)?.addOnCompleteListener {
            presenter.logincomplete()
        }?.addOnFailureListener {
            presenter.loginfail()
            Log.d("hata",it.message)
        }?.addOnCanceledListener {
            presenter.loginfail()
        }
    }
}