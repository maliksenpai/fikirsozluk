package com.fikir.Presenter

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Firebase.MainMenu
import com.fikir.UI.Activities.LoginActivity
import com.fikir.UI.Activities.MainActivity
import com.fikir.UI.Fragments.NewPostFragment
import com.google.firebase.auth.FirebaseAuth

class MainPresenter {
     lateinit var mainActivity: MainActivity
    lateinit var postfragment:NewPostFragment
    fun init(mainActivity: MainActivity){
        this.mainActivity=mainActivity
    }
    fun initpostfragment(postFragment: NewPostFragment){
        this.postfragment=postFragment
    }
    fun giris(){
        var auth =FirebaseAuth.getInstance()
        var account=auth.currentUser
        if(account==null){
            mainActivity.startActivity(Intent(mainActivity.applicationContext, LoginActivity::class.java))
        }
        else{
            Log.d("gelen","asda")
        }
    }
    fun showpostfragmentpresenter(){
        mainActivity.showpostfragmentmain()
    }
    fun postlist(recyclerView: RecyclerView){
        MainMenu().listsubject(recyclerView,mainActivity)
    }
}