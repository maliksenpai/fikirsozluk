package com.fikir.Model.Firebase

import com.fikir.Model.Singletons.AuthSingleton
import com.fikir.Model.Singletons.DatabaseSingleton

class RegisterAcc {
    fun register(nickname:String,email:String,pass:String){
        val auth=AuthSingleton().getInstance()
        val database=DatabaseSingleton().getInstance()?.child("kullanicilar")?.push()
        auth?.createUserWithEmailAndPassword(email,pass)
            ?.addOnCompleteListener {
                database?.child("kullanicilar")?.push()
                database?.child("eposta")?.setValue(email)
                database?.child("nick")?.setValue(nickname)
                database?.child("yazilar")?.setValue(null)
                database?.child("takipciler")?.setValue(null)
                database?.child("takipedilenler")?.setValue(null)
                database?.push()
            }
    }
}