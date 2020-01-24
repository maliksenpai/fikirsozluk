package com.fikir.Model.Firebase

import android.util.Log
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NicknameAcc {
    fun getNickname(mail:String,subject:String,text:String,context: View){
        val database= FirebaseDatabase.getInstance().getReference().child("kullanicilar")
        var nickname: String
        val listener=object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    Log.d("gelennick",it.child("nick").getValue().toString())
                    Log.d("gelennick3",mail)
                    if(mail.equals(it.child("eposta").getValue().toString())){
                        nickname=it.child("nick").getValue().toString()
                        Log.d("gelennick2",nickname)
                        MainMenu().newPostCheckSubject(nickname,subject,text,context)
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d("gelennick",p0.message)
            }
        }
        database.addValueEventListener(listener)
    }
}