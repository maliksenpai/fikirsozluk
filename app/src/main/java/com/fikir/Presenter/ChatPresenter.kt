package com.fikir.Presenter

import android.util.Log
import com.fikir.Model.Firebase.Chat
import com.fikir.UI.Activities.ChatActivity

class ChatPresenter {
    fun checkmessage(activity:ChatActivity,sendnick:String,recievenick:String,text:String){
        Chat().sendmessage(activity,sendnick,recievenick,text)
        happycheck(activity,sendnick,recievenick,text)
        sadcheck(activity,sendnick,recievenick,text)
        lovelycheck(activity,sendnick,recievenick,text)
        angrycheck(activity,sendnick,recievenick,text)
    }
    fun happycheck(chat:ChatActivity,sendnick:String,recievenick:String,text:String){
        var string1="\uD83D\uDE02"
        var string2="\uD83D\uDE04"
        var string3="\uD83D\uDE01"
        var string4=":D"
        var string5="\uD83E\uDD23"
       // Log.d("gelencheck",(text.indexOf(string)).toString())
        Log.d("gelenchech",text.indexOf(string4).toString())
        if(text.indexOf(string2)>=0){
            Chat().inchappy(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string2)>=0){
            Chat().inchappy(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string3)>=0){
            Chat().inchappy(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string4)>=0){
            Chat().inchappy(chat,sendnick,recievenick,text)
            Log.d("gelenchat15","oldu")
        }
        else if(text.indexOf(string5)>=0){
            Chat().inchappy(chat,sendnick,recievenick,text)
        }
    }
    fun sadcheck(chat:ChatActivity,sendnick:String,recievenick:String,text:String){
        var string1="üzgünüm"
        var string2="malesef"
        var string3=".s"
        var string4="peki"
        var string5="üzdü"
        if(text.indexOf(string1)>=0){
            Chat().incsad(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string2)>=0){
            Chat().incsad(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string3)>=0){
            Chat().incsad(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string4)>=0){
            Chat().incsad(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string5)>=0){
            Chat().incsad(chat,sendnick,recievenick,text)
        }
    }
    fun angrycheck(chat:ChatActivity,sendnick:String,recievenick:String,text:String){
        var string1="amk"
        var string2="oç"
        var string3="sikim"
        var string4="çocuğu"
        var string5="evladı"
        if(text.indexOf(string1)>=0){
            Chat().incangry(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string2)>=0){
            Chat().incangry(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string3)>=0){
            Chat().incangry(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string4)>=0){
            Chat().incangry(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string5)>=0) {
            Chat().incangry(chat, sendnick, recievenick, text)
        }
    }
    fun lovelycheck(chat:ChatActivity,sendnick:String,recievenick:String,text:String){
        var string1="seviyorum"
        var string2="aşkım"
        var string3="canım"
        var string4="bitanem"
        var string5="balım"
        if(text.indexOf(string2)>=0){
            Chat().inclovely(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string2)>=0){
            Chat().inclovely(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string3)>=0){
            Chat().inclovely(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string4)>=0){
            Chat().inclovely(chat,sendnick,recievenick,text)
        }
        else if(text.indexOf(string5)>=0){
            Chat().inclovely(chat,sendnick,recievenick,text)
        }
    }
}