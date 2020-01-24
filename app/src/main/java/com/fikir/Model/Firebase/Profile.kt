package com.fikir.Model.Firebase

import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.fikir.Model.Adapters.PostAdapter
import com.fikir.Model.Singletons.DatabaseSingleton
import com.fikir.R
import com.fikir.UI.Activities.SearchProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_search_profile.view.*
import java.util.*

class Profile {
    fun update(activity:SearchProfile,nick:String){
        activity.findViewById<TextView>(R.id.profilnick).setText(nick)
       /* val firebase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if (nick.equals(it.child("nick").getValue().toString())) {
                        Log.d("gelenmain", it.child("nick").getValue().toString());
                        Log.d("gelenmain",it.key)
                        var asd=firebase?.child(it.key.toString())?.child("postlar")
                        val listenerposts = object : ValueEventListener { //epostasıyla profilini bulduğumuz hesabın içine giriyoruz
                            override fun onCancelled(p1: DatabaseError) {
                            }

                            override fun onDataChange(p1: DataSnapshot) {
                                activity.findViewById<TextView>(R.id.profilpostsayisisayac).setText(p1.childrenCount.toString()) //profil ekranına post sayısını yazar
                                Log.d("gelenmain", p1.childrenCount.toString())
                            }
                        }
                        asd?.addListenerForSingleValueEvent(listenerposts)
                    }
                }
            }
        }
        firebase?.addValueEventListener(listener) */
    }
    fun follow(activity:SearchProfile,nick:String){
        val firebase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        var mail= FirebaseAuth.getInstance().currentUser?.email.toString()
        var nickname=""
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("gelen9",p0.message)
            }
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(mail.equals(it.child("eposta").getValue().toString())){
                        nickname=it.child("nick").getValue().toString()
                    }
                }
                p0.children.forEach {
                    if(it.child("nick").getValue().toString().equals(nickname)){
                        firebase?.child(it.key.toString())?.child("takipedilen")?.push()?.setValue(nick)
                    }
                    if(it.child("nick").getValue().toString().equals(nick)){
                        firebase?.child(it.key.toString())?.child("takipci")?.push()?.setValue(nickname)
                    }
                }
            }
        }
        firebase?.addListenerForSingleValueEvent(listener)
    }
    fun unfollow(activity:SearchProfile,nick:String){
        val firebase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        var mail= FirebaseAuth.getInstance().currentUser?.email.toString()
        var nickname=""
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("gelen9",p0.message)
            }
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(mail.equals(it.child("eposta").getValue().toString())){
                        nickname=it.child("nick").getValue().toString()
                    }
                }
                p0.children.forEach {
                    it.child("takipci").children.forEach {
                        Log.d("gelenarama10",it.getValue().toString())
                        if(it.getValue().toString().equals(nickname)){
                            firebase?.removeValue()
                        }
                    }
                    if(it.child("takipedilen").getValue().toString().equals(nick)){
                        Log.d("gelenarama7","oldu")
                        firebase?.child(it.key.toString())?.child("takipedilen")?.push()?.setValue(null)
                    }
                    if(it.child("takipci").getValue().toString().equals(nickname)){
                        Log.d("gelenaramaa8","oldu")
                        firebase?.child(it.key.toString())?.child("takipci")?.push()?.setValue(null)
                    }
                }
            }
        }
        firebase?.addListenerForSingleValueEvent(listener)
    }
}