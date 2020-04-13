package com.fikir.Model.Firebase

import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Adapters.PostAdapter
import com.fikir.Model.Adapters.ProfileAdapter
import com.fikir.Model.Module.PostModule
import com.fikir.Model.Singletons.DatabaseSingleton
import com.fikir.R
import com.fikir.UI.Activities.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

class MainMenu {
    fun newPost(nick: String, subject: String, text: String) {
        val firebasepost = DatabaseSingleton().getInstance()?.child("postlar")?.push()
        val nickdatabase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        val listener = object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    p0.children.forEach {
                        if (nick.equals(it.child("nick").getValue().toString())) {
                            Log.d("gelenread",it.key)
                            var nickname = it.child("nick").getValue().toString()
                            Log.d("gelenread", nickname)
                            nickdatabase?.child(it.key.toString())?.child("postlar")?.push()?.setValue(PostModule(subject, nickname!!))
                            //  nickdatabase?.push()
                        }
                    }
                }

        }
        nickdatabase?.addListenerForSingleValueEvent(listener)
        firebasepost?.child("postlar")?.push()
        firebasepost?.child("yazar")?.setValue(nick)
        firebasepost?.child("konu")?.setValue(subject)
        firebasepost?.child("yazi")?.setValue(text)
        firebasepost?.child("zaman")?.setValue(Date())
        firebasepost?.push()
        val firebasesubject = DatabaseSingleton().getInstance()?.child("konular")?.push()
        firebasesubject?.child("konular")?.push()
        firebasesubject?.child("konu")?.setValue(subject)
    }

    fun newPostCheckSubject(nick: String, subject: String, text: String, context: View) {
        val database = DatabaseSingleton().getInstance()?.child("konular")
        var i = 0;
        Log.d("gelen9","olmadı")
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("gelen9",p0.message)
            }
            override fun onDataChange(p0: DataSnapshot) {
                Log.d("gelen9","olmadı2")
                p0.children.forEach {
                    Log.d("gelen9",it.child("konu").getValue().toString())
                    if (subject.equals(it.child("konu").getValue().toString())) {
                        i++
                    }
                }
                if(i==0){
                    newPost(nick,subject,text)
                }else{
                    Toast.makeText(context.context,"Aynı konu ismi var",Toast.LENGTH_SHORT).show()
                }
            }
        }
        database?.addValueEventListener(listener)
    }
    fun listsubject(recyclerView: RecyclerView, mainActivity: MainActivity){
        var list:MutableList<String> = arrayListOf()
        val database = DatabaseSingleton().getInstance()?.child("konular")
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("gelen9",p0.message)
            }
            override fun onDataChange(p0: DataSnapshot) {
                Log.d("gelen9","olmadı2")
                p0.children.forEach {
                    Log.d("gelen9",it.child("konu").getValue().toString())
                    list.add(it.child("konu").getValue().toString())
                }
//                Log.d("gelen9",list[0])
                recyclerView.layoutManager=LinearLayoutManager(mainActivity.applicationContext)
                recyclerView.addItemDecoration(DividerItemDecoration(mainActivity.applicationContext,DividerItemDecoration.VERTICAL))
                recyclerView.setHasFixedSize(true)
                recyclerView.adapter=PostAdapter(list)
            }
        }
        database?.addListenerForSingleValueEvent(listener)
    }
    fun profildetails(mainActivity:MainActivity){
        val firebase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        var mail= FirebaseAuth.getInstance().currentUser?.email.toString()
        var list:MutableList<String> = arrayListOf()
        var recyclerView=mainActivity.findViewById<RecyclerView>(R.id.profilelist)
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if (mail.equals(it.child("eposta").getValue().toString())) {
                        mainActivity.findViewById<TextView>(R.id.profilnick).setText(it.child("nick").getValue().toString())

                        var postcount=firebase?.child(it.key.toString())?.child("postlar")
                        val listenerpostcount = object : ValueEventListener { //epostasıyla profilini bulduğumuz hesabın içine giriyoruz
                            override fun onCancelled(p1: DatabaseError) {
                            }

                            override fun onDataChange(p1: DataSnapshot) {
                                mainActivity.findViewById<TextView>(R.id.profilpostsayisisayac).setText(p1.childrenCount.toString()) //profil ekranına post sayısını yazar
                                Log.d("gelenmain", p1.childrenCount.toString())
                            }
                        }

                        var follower=firebase?.child(it.key.toString())?.child("takipci")
                        val listenerfollower= object :ValueEventListener{
                            override fun onCancelled(p2: DatabaseError) {

                            }

                            override fun onDataChange(p2: DataSnapshot) {
                                mainActivity.findViewById<TextView>(R.id.profiltakipcisayac).setText(p2.childrenCount.toString())
                            }

                        }

                        var followed=firebase?.child(it.key.toString())?.child("takipedilen")
                        val listenerfollowed = object :ValueEventListener{
                            override fun onCancelled(p3: DatabaseError) {

                            }

                            override fun onDataChange(p3: DataSnapshot) {
                                mainActivity.findViewById<TextView>(R.id.profiltakipedilensayac).setText(p3.childrenCount.toString())
                            }

                        }

                        var posts=firebase?.child(it.key.toString())?.child("postlar")
                        val listenerposts = object :ValueEventListener{
                            override fun onCancelled(p3: DatabaseError) {

                            }

                            override fun onDataChange(p3: DataSnapshot) {
                                var i=0
                                p3.children.forEach {
                                    Log.d("gelenarama14",it.childrenCount.toString())
                                    if(it.childrenCount<5){
                                        if(i<it.childrenCount){
                                            list.add(it?.child("subject")?.getValue().toString())
                                            Log.d("gelenarama14","oldu")
                                        }
                                    }
                                    else{
                                        if(i<5){
                                            list.add(it?.child("subject")?.getValue().toString())
                                            Log.d("gelenarama14","oldu")
                                        }
                                    }
                                    i++
                                }
                                recyclerView.layoutManager=LinearLayoutManager(mainActivity.applicationContext)
                                recyclerView.addItemDecoration(DividerItemDecoration(mainActivity.applicationContext,DividerItemDecoration.VERTICAL))
                                recyclerView.adapter= ProfileAdapter(list)
                            }
                        }

                        posts?.addListenerForSingleValueEvent(listenerposts)
                        postcount?.addListenerForSingleValueEvent(listenerpostcount)
                        follower?.addListenerForSingleValueEvent(listenerfollower)
                        followed?.addListenerForSingleValueEvent(listenerfollowed)

                    }
                }
            }
        }
        firebase?.addValueEventListener(listener)


    }
    fun exitaccount(){
        var user=FirebaseAuth.getInstance()
        user.signOut()
    }
}