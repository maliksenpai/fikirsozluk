package com.fikir.Model.Firebase

import android.app.Notification
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Adapters.ChatAdapter
import com.fikir.Model.Adapters.ReadAdapter
import com.fikir.Model.Module.ChatModule
import com.fikir.Model.Singletons.DatabaseSingleton
import com.fikir.R
import com.fikir.UI.Activities.ChatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_chat.view.*

class Chat {
    fun update(chat:ChatActivity,sendnick:String,recievenick:String){
        var list:MutableList<ChatModule> = arrayListOf()
        val firebase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        val recyclerview= chat.findViewById<RecyclerView>(R.id.chatlist)
        ChatAdapter(list).cleardata()
        Status().setonline(FirebaseAuth.getInstance().currentUser?.email.toString())
        Log.d("gelenat",recievenick + "-" + sendnick)
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    Log.d("gelenchat",it.getValue().toString())
                    if(it.child("nick").getValue().toString().equals(sendnick)) {
                        it.child("mesajlar").child(recievenick).children.forEach {
                            Log.d("gelenchat", it.child("mesaj").getValue().toString())
                            list.add(ChatModule(it.child("gonderen").getValue().toString(), it.child("mesaj").getValue().toString()))
                        }
                    }
                }
                val manager=LinearLayoutManager(chat)
                manager.stackFromEnd=true
                recyclerview.layoutManager= manager
                recyclerview.addItemDecoration(DividerItemDecoration(chat.applicationContext, DividerItemDecoration.VERTICAL))
                recyclerview.setHasFixedSize(true)
                recyclerview.adapter= ChatAdapter(list)
            }
        }
        firebase?.addListenerForSingleValueEvent(listener)
    }
    fun sendmessage(chat:ChatActivity,sendnick:String,recievenick:String,text:String){
        val nickdatabase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("nick").getValue().toString().equals(sendnick)){
                        var db= it.ref.child("mesajlar").child(recievenick).push()
                        db.child("mesaj").setValue(text)
                        db.child("gonderen").setValue(sendnick)
                    }
                    if(it.child("nick").getValue().toString().equals(recievenick)){
                        var db= it.ref.child("mesajlar").child(sendnick).push()
                        db.child("mesaj").setValue(text)
                        db.child("gonderen").setValue(sendnick)
                    }
                }
                update(chat,sendnick,recievenick)
            }
        }
        nickdatabase?.addListenerForSingleValueEvent(listener)
    }
    fun inchappy(chat:ChatActivity,sendnick:String,recievenick:String,text:String){
        val nickdatabase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        val listener= object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("nick").getValue().toString().equals(sendnick)){
                        var db = it.ref.child("happy").push()
                        db.setValue(text)
                    }
                }
            }

        }
        nickdatabase?.addListenerForSingleValueEvent(listener)
    }
    fun incsad(chat:ChatActivity,sendnick:String,recievenick:String,text:String){
        val nickdatabase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        val listener= object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("nick").getValue().toString().equals(sendnick)){
                        var db = it.ref.child("sad").push()
                        db.setValue(text)
                    }
                }
            }

        }
        nickdatabase?.addListenerForSingleValueEvent(listener)
    }
    fun incangry(chat:ChatActivity,sendnick:String,recievenick:String,text:String){
        val nickdatabase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        val listener= object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("nick").getValue().toString().equals(sendnick)){
                        var db = it.ref.child("angry").push()
                        db.setValue(text)
                    }
                }
            }

        }
        nickdatabase?.addListenerForSingleValueEvent(listener)
    }
    fun inclovely(chat:ChatActivity,sendnick:String,recievenick:String,text:String){
        val nickdatabase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        Log.d("gelenchat15","oldu")
        val listener= object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("nick").getValue().toString().equals(sendnick)){
                        var db = it.ref.child("lovely").push()
                        db.setValue(text)
                    }
                }
            }

        }
        nickdatabase?.addListenerForSingleValueEvent(listener)
    }
}