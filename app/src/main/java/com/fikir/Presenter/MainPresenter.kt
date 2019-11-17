package com.fikir.Presenter

import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Firebase.NicknameAcc
import com.fikir.Model.Firebase.Post
import com.fikir.UI.Activities.Login
import com.fikir.UI.Activities.Main
import com.fikir.UI.Fragments.NewPostFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import java.time.LocalDateTime
import java.util.*

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
        Post().listsubject(recyclerView,main)
    }
}