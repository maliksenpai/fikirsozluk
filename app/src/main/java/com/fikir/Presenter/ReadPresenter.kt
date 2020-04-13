package com.fikir.Presenter

import android.util.Log
import com.fikir.Model.Firebase.ReadList
import com.fikir.UI.Activities.ReadPostActivity

class ReadPresenter {
    lateinit var readpost:ReadPostActivity
    fun init(readpost:ReadPostActivity){
        this.readpost=readpost
    }
    fun readnewpost(text:String,time:String,nick:String,subject:String){
        Log.d("gelenread",nick)
        Log.d("gelenread",subject)
        ReadList().readnewpost(text,nick,time,subject)
    }
}