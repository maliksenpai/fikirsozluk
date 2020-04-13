package com.fikir.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.fikir.Model.Firebase.Chat
import com.fikir.Model.Firebase.Status
import com.fikir.Presenter.ChatPresenter
import com.fikir.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        Status().setonline(FirebaseAuth.getInstance().currentUser?.email.toString())
        val recievenick=intent.getStringExtra("recievenick")
        val sendnick=intent.getStringExtra("sendnick")
        Chat().update(this,sendnick,recievenick)
      //  findViewById<ImageButton>(R.id.chatbutton).setOnClickListener { Chat().sendmessage(this,sendnick,recievenick, findViewById<EditText>(R.id.chattext).text.toString()) }
        findViewById<ImageButton>(R.id.chatbutton).setOnClickListener {
            ChatPresenter().checkmessage(this,sendnick,recievenick, findViewById<EditText>(R.id.chattext).text.toString())
            findViewById<EditText>(R.id.chattext).setText("")
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
