package com.fikir.Presenter

import android.util.Log
import com.fikir.Model.Firebase.ReadList
import com.fikir.UI.Activities.ReadPost

class ReadPresenter {
    lateinit var readpost:ReadPost
    fun init(readpost:ReadPost){
        this.readpost=readpost
    }
    fun readnewpost(text:String,time:String,nick:String,subject:String){
        Log.d("gelenread",nick)
        Log.d("gelenread",subject)
        ReadList().readnewpost(text,nick,time,subject)
    }
}