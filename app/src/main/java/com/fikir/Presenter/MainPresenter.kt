package com.fikir.Presenter

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Firebase.MainMenu
import com.fikir.UI.Activities.Login
import com.fikir.UI.Activities.Main
import com.fikir.UI.Fragments.NewPostFragment
import com.google.firebase.auth.FirebaseAuth

class MainPresenter {
     lateinit var main: Main
    lateinit var postfragment:NewPostFragment
    fun init(main: Main){
        this.main=main
    }
    fun initpostfragment(postFragment: NewPostFragment){
        this.postfragment=postFragment
    }
    fun giris(){
        var auth =FirebaseAuth.getInstance()
        var account=auth.currentUser
        if(account==null){
            main.startActivity(Intent(main.applicationContext, Login::class.java))
        }
        else{
            Log.d("gelen","asda")
        }
    }
    fun showpostfragmentpresenter(){
        main.showpostfragmentmain()
    }
    fun postlist(recyclerView: RecyclerView){
        MainMenu().listsubject(recyclerView,main)
    }
}