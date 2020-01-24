package com.fikir.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.fikir.Model.Adapters.SearchAdapter
import com.fikir.Model.Firebase.Profile
import com.fikir.Presenter.SearchPresenter
import com.fikir.R

class SearchProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_profile)
        val nick=intent.getStringExtra("nickname")
        Profile().update(this,nick)
        findViewById<Button>(R.id.profiltakip).setOnClickListener {
            SearchPresenter().followbutton(this,nick)
        }
        findViewById<Button>(R.id.profilmesaj).setOnClickListener {
            //gelecek
        }
    }
}
