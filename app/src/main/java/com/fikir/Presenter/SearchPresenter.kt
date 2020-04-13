package com.fikir.Presenter

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Button
import com.fikir.Model.Firebase.Profile
import com.fikir.R
import com.fikir.UI.Activities.SearchProfileActivity

class SearchPresenter {
    @SuppressLint("ResourceAsColor")
    fun followbutton(activity:SearchProfileActivity, nick:String){
        val button = activity.findViewById<Button>(R.id.profiltakip)
        Log.d("gelenarama5", button.text.toString())
        if(button.text.toString()=="Takip Ediliyor"){
            Log.d("gelenarama5","ikincibutton")
            button.text="Takip Et"
            button.setBackgroundResource(R.color.colorPrimary)
            Profile().unfollow(activity,nick)
        }
        else if(button.text.toString()=="Takip Et"){
            Log.d("gelenarama5","ilkbutton")
            button.text="Takip Ediliyor"
            button.setBackgroundResource(R.color.colorPrimaryDark)
            Profile().follow(activity,nick)
        }

    }
}