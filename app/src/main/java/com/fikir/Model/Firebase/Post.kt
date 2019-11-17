package com.fikir.Model.Firebase

import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Adapters.PostAdapter
import com.fikir.Model.Module.PostModule
import com.fikir.Model.Singletons.DatabaseSingleton
import com.fikir.UI.Activities.Main
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Post {
    fun newPost(nick: String, subject: String, text: String) {
        val firebasepost = DatabaseSingleton().getInstance()?.child("postlar")?.push()
        firebasepost?.child("postlar")?.push()
        firebasepost?.child("yazar")?.setValue(nick)
        firebasepost?.child("konu")?.setValue(subject)
        firebasepost?.child("yazi")?.setValue(text)
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
    fun listsubject(recyclerView: RecyclerView,main: Main){
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
                Log.d("gelen9",list[0])
                recyclerView.layoutManager=LinearLayoutManager(main.applicationContext)
                recyclerView.adapter=PostAdapter(list)
            }
        }
        database?.addValueEventListener(listener)
    }
}