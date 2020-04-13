package com.fikir.Model.Firebase

import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Singletons.DatabaseSingleton
import com.fikir.R
import com.fikir.UI.Activities.ChatActivity
import com.fikir.UI.Activities.SearchProfileActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class Profile {
    fun update(activity:SearchProfileActivity, nick:String){
        activity.findViewById<TextView>(R.id.profilnick).setText(nick)
        var mail= FirebaseAuth.getInstance().currentUser?.email.toString()
        val firebase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        var nickname=""
        var list:MutableList<String> = arrayListOf()
        var recyclerView=activity.findViewById<RecyclerView>(R.id.profilelist)
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(mail.equals(it.child("eposta").getValue().toString())){
                        nickname=it.child("nick").getValue().toString()
                        Log.d("gelenarama13","oldu")
                    }
                }
                p0.children.forEach {
                    if (nick.equals(it.child("nick").getValue().toString())) {
                        Log.d("gelenmain", it.child("nick").getValue().toString());
                        Log.d("gelenmain",it.key)
                        var postcount=firebase?.child(it.key.toString())?.child("postlar")
                        val listenerpostcount = object : ValueEventListener { //epostasıyla profilini bulduğumuz hesabın içine giriyoruz
                            override fun onCancelled(p1: DatabaseError) {
                            }

                            override fun onDataChange(p1: DataSnapshot) {
                                activity.findViewById<TextView>(R.id.profilpostsayisisayac).setText(p1.childrenCount.toString()) //profil ekranına post sayısını yazar
                                Log.d("gelenmain", p1.childrenCount.toString())
                            }
                        }

                        var follower=firebase?.child(it.key.toString())?.child("takipci")
                        val listenerfollower= object :ValueEventListener{
                            override fun onCancelled(p2: DatabaseError) {

                            }

                            override fun onDataChange(p2: DataSnapshot) {
                                activity.findViewById<TextView>(R.id.profiltakipcisayac).setText(p2.childrenCount.toString())
                                p2.children.forEach {
                                    Log.d("gelenarama13",it.getValue().toString() + nickname)
                                    if(nickname.equals(it.getValue().toString())){
                                        Log.d("gelenarama13","asd")
                                        activity.findViewById<Button>(R.id.profiltakip).text="Takip Ediliyor"
                                        activity.findViewById<Button>(R.id.profiltakip).setBackgroundResource(R.color.colorPrimaryDark)
                                    }
                                }
                            }

                        }

                        var followed=firebase?.child(it.key.toString())?.child("takipedilen")
                        val listenerfollowed = object :ValueEventListener{
                            override fun onCancelled(p3: DatabaseError) {

                            }

                            override fun onDataChange(p3: DataSnapshot) {
                                activity.findViewById<TextView>(R.id.profiltakipedilensayac).setText(p3.childrenCount.toString())
                            }

                        }
                        var posts=firebase?.child(it.key.toString())?.child("postlar")
                        val listenerposts = object :ValueEventListener{
                            override fun onCancelled(p3: DatabaseError) {

                            }

                            override fun onDataChange(p3: DataSnapshot) {
                                var i=0
                                p3.children.forEach {
                                    Log.d("gelenarama14",it.child("subject")?.getValue().toString())
                                    if(i<5) {
                                        list.add(it?.child("subject")?.getValue().toString())
                                    }
                                    i++
                                }
                            }
                        }

                        posts?.addListenerForSingleValueEvent(listenerposts)
                        postcount?.addListenerForSingleValueEvent(listenerpostcount)
                        follower?.addListenerForSingleValueEvent(listenerfollower)
                        followed?.addListenerForSingleValueEvent(listenerfollowed)

                      /*  recyclerView.layoutManager=LinearLayoutManager(activity.applicationContext)
                        recyclerView.adapter=ProfileAdapter(list) */
                    }
                }
            }
        }
        firebase?.addValueEventListener(listener)
    }
    fun follow(activity:SearchProfileActivity, nick:String){
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
    fun unfollow(activity:SearchProfileActivity, nick:String){
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
                            Log.d("gelenarama10","zxc")
                            it.ref.removeValue()
                        }
                    }
                    it.child("takipedilen").children.forEach {
                        Log.d("gelenarama11",it.getValue().toString())
                        if(it.getValue().toString().equals(nick)){
                            Log.d("gelenarama11","zxc")
                            it.ref.removeValue()
                        }
                    }
                  /*  if(it.child("takipedilen").getValue().toString().equals(nick)){
                        Log.d("gelenarama7","oldu")
                        firebase?.child(it.key.toString())?.child("takipedilen")?.push()?.setValue(null)
                    }
                    if(it.child("takipci").getValue().toString().equals(nickname)){
                        Log.d("gelenaramaa8","oldu")
                        firebase?.child(it.key.toString())?.child("takipci")?.push()?.setValue(null)
                    } */
                }
            }
        }
        firebase?.addListenerForSingleValueEvent(listener)
    }
    fun message(profile:SearchProfileActivity,nick:String){
        val firebase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        var mail= FirebaseAuth.getInstance().currentUser?.email.toString()
        var nickname=""
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(mail.equals(it.child("eposta").getValue().toString())){
                        nickname=it.child("nick").getValue().toString()
                    }
                }
                var intent=Intent(profile.applicationContext,ChatActivity::class.java)
                intent.putExtra("sendnick",nickname)
                intent.putExtra("recievenick",nick)
                profile.startActivity(intent)
            }

        }
        firebase?.addListenerForSingleValueEvent(listener)
    }
}