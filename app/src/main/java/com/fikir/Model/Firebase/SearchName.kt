package com.fikir.Model.Firebase

import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Adapters.SearchAdapter
import com.fikir.Model.Module.SearchModule
import com.fikir.Model.Singletons.DatabaseSingleton
import com.fikir.UI.Activities.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchName {
    fun names(liste:RecyclerView,text:String,count:Int,search:MainActivity){
        val firebaseread = DatabaseSingleton().getInstance()?.child("kullanicilar")
        var nicks:MutableList<String> = arrayListOf()
        var list:MutableList<SearchModule> = arrayListOf()
        var i=0
        var n=0
        SearchAdapter(list).cleardata()
        val database= FirebaseDatabase.getInstance().getReference().child("kullanicilar")
        var mail= FirebaseAuth.getInstance().currentUser?.email.toString()
        var nickname: String=""
        val listener = object :ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if (mail.equals(it.child("eposta").getValue().toString())) {
                        nickname = it.child("nick").getValue().toString()
                    }
                }
                p0.children.forEach {
                    //profil listesinden çekmek için
                    //nicks.add(it.child("nick").getValue().toString())
                    i=0
                    n=0
                    Log.d("gelenarama4",nickname)
                    while(i<count){
                        if(text[i].toString()==it.child("nick").getValue().toString()[i].toString()){
                            Log.d("gelenarama",i.toString())
                            Log.d("gelenarama2",text[i].toString())
                            Log.d("gelenarama2",it.child("nick").getValue().toString()[i].toString())
                            n++
                        }
                        i++
                    }
                    if(i==n && !(it.child("nick").getValue().toString().equals(nickname))){
                       // list.add(it.child("nick").getValue().toString())
                        list.add(SearchModule(it.child("nick").getValue().toString(),it.child("status").getValue().toString()))
                    }
                }
                liste.layoutManager=LinearLayoutManager(search)
                liste.addItemDecoration(DividerItemDecoration(search.applicationContext, DividerItemDecoration.VERTICAL))
                liste.adapter=SearchAdapter(list)
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        }
        firebaseread?.addListenerForSingleValueEvent(listener)
    }
}