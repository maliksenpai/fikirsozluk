package com.fikir.UI.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.fikir.Model.Firebase.Profile
import com.fikir.Model.Firebase.Status
import com.fikir.Presenter.SearchPresenter
import com.fikir.R
import com.google.firebase.auth.FirebaseAuth

class SearchProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_profile)
        val nick=intent.getStringExtra("nickname")
        Profile().update(this,nick)
        Status().setonline(FirebaseAuth.getInstance().currentUser?.email.toString())
        findViewById<Button>(R.id.profiltakip).setOnClickListener {
            SearchPresenter().followbutton(this,nick)
        }
        findViewById<Button>(R.id.profilmesaj).setOnClickListener {
           // startActivity(Intent(applicationContext,ChatActivity::class.java))
            Profile().message(this,nick)
        }
    }
    override fun onStop() {
        Status().setoffline(FirebaseAuth.getInstance().currentUser?.email.toString())
        super.onStop()
    }
    override fun onResume() {
        Status().setonline(FirebaseAuth.getInstance().currentUser?.email.toString())
        super.onResume()
    }
}
