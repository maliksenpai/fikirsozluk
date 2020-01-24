package com.fikir.Model.Firebase

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Adapters.ReadAdapter
import com.fikir.Model.Module.PostModule
import com.fikir.Model.Module.ReadModule
import com.fikir.Model.Singletons.DatabaseSingleton
import com.fikir.UI.Activities.ReadPost
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList

class ReadList {
    fun postlist(subject:String, recyclerview:RecyclerView, post: ReadPost){
        val firebaseread = DatabaseSingleton().getInstance()?.child("postlar")
        var list:MutableList<ReadModule> = arrayListOf()
        ReadAdapter(list).cleardata()
        val listener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                Log.d("gelenpost",p0.getValue().toString())
                p0.children.forEach {
                    if(it.child("konu").getValue().toString().equals(subject)){
                        Log.d("gelenpost","oldu")
                        Log.d("gelenpost2",it.child("zaman").child("date").getValue().toString())
                        list.add(ReadModule(it.child("yazi").getValue().toString()
                            ,it.child("zaman").child("date").getValue().toString() + "/" +it.child("zaman").child("month").getValue().toString() + "/" + it.child("zaman").child("year").getValue().toString()
                            ,it.child("yazar").getValue().toString()))
                        Log.d("gelenpost",list.get(0).time)
                    }
                }
                recyclerview.layoutManager= LinearLayoutManager(post)
                recyclerview.adapter= ReadAdapter(list)
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        }
        firebaseread?.addListenerForSingleValueEvent(listener)
    }
    fun readnewpost(text:String,mail:String,time:String,subject:String) {
        /*    fuckenesmalikacun;
        fuckhimdeepintheass;
        hesabarbiegirl;
        inthebarbieworld;
        lifeinplasticisfantastic; */
        val nickdatabase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        var nickname: String? = null
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    Log.d("gelennick", it.child("nick").getValue().toString())
                    Log.d("gelennick3", mail)
                    if (mail.equals(it.child("eposta").getValue().toString())) {
                        Log.d("gelenread",it.key)
                        nickname = it.child("nick").getValue().toString()
                        Log.d("gelenread", nickname)
                        val postdatabase = DatabaseSingleton().getInstance()?.child("postlar")?.push()
                        postdatabase?.child("postlar")?.push()
                        postdatabase?.child("yazar")?.setValue(nickname)
                        postdatabase?.child("konu")?.setValue(subject)
                        postdatabase?.child("yazi")?.setValue(text)
                        postdatabase?.child("zaman")?.setValue(Date())
                        postdatabase?.push()
                        nickdatabase?.child(it.key.toString())?.child("postlar")?.push()?.setValue(PostModule(subject, nickname!!))
                        //  nickdatabase?.push()
                    }
                }
            }

        }
        nickdatabase?.addListenerForSingleValueEvent(listener)
        //readnewpost()

    }}
