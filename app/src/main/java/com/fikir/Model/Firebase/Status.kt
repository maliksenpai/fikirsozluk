package com.fikir.Model.Firebase

import com.fikir.Model.Singletons.DatabaseSingleton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class Status {
    fun setonline(mail:String){
        val nickdatabase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        val listener= object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("eposta").getValue().toString().equals(mail)){
                        it.ref.child("status").setValue("1")
                    }
                }
            }
        }
        nickdatabase?.addListenerForSingleValueEvent(listener)
    }
    fun setoffline(mail:String){
        val nickdatabase = DatabaseSingleton().getInstance()?.child("kullanicilar")
        val listener= object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    if(it.child("eposta").getValue().toString().equals(mail)){
                        it.ref.child("status").setValue("0")
                    }
                }
            }
        }
        nickdatabase?.addListenerForSingleValueEvent(listener)
    }
}