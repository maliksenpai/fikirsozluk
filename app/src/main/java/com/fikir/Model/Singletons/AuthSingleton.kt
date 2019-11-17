package com.fikir.Model.Singletons

import com.google.firebase.auth.FirebaseAuth

class AuthSingleton {
    var auth:FirebaseAuth?=null
    fun getInstance():FirebaseAuth?{
        if(auth==null){
            auth= FirebaseAuth.getInstance()
        }
        return auth
    }
}