package com.fikir.Model.Singletons

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DatabaseSingleton {
    var database:DatabaseReference?=null
    fun getInstance():DatabaseReference?{
        if(database==null){
            database=FirebaseDatabase.getInstance().getReference()
        }
        return database
    }
}