package com.fikir.UI.Activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Firebase.ReadList
import com.fikir.Presenter.ReadPresenter
import com.fikir.R
import com.fikir.UI.Fragments.ReadPostNewPost
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class ReadPost : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_post)
        val zaman: Date =Date()
        Log.d("gelenpost",zaman.toString())
        val subject=intent.getStringExtra("subject")
        Log.d("gelenpost",subject)
        val presenter=ReadPresenter()
        presenter.init(this)
        ReadList().postlist(subject,findViewById<RecyclerView>(R.id.readpostlist),this)
        findViewById<FloatingActionButton>(R.id.postnewpost).setOnClickListener {
            findViewById<FloatingActionButton>(R.id.postnewpost).visibility=View.INVISIBLE
            findViewById<EditText>(R.id.readnewposttext).visibility=View.VISIBLE
            findViewById<FloatingActionButton>(R.id.readnewpostbutton).visibility=View.VISIBLE
        }
        findViewById<FloatingActionButton>(R.id.readnewpostbutton).setOnClickListener {
            Log.d("gelenread","asdasd")
            ReadPresenter().readnewpost(
                findViewById<EditText>(R.id.readnewposttext).text.toString()
            , zaman.toString()
            ,FirebaseAuth.getInstance().currentUser?.email.toString()
            ,subject)
        }
    }

}
