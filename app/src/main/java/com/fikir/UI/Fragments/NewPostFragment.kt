package com.fikir.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.fikir.Model.Firebase.NicknameAcc
import com.fikir.Presenter.MainPresenter
import com.fikir.R
import com.google.firebase.auth.FirebaseAuth

class NewPostFragment: DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val presenter=MainPresenter()
        presenter.initpostfragment(this)
        val view=inflater.inflate(R.layout.newpostfragment,null)
      /*  view.findViewById<Button>(R.id.postfragmentbutton).setOnClickListener { presenter.newpost(
            view.findViewById<EditText>(R.id.postfragmentsubject).text.toString(),
            view.findViewById<EditText>(R.id.postfragmenttext).text.toString()
        ) } */
        view.findViewById<Button>(R.id.postfragmentbutton).setOnClickListener {
            NicknameAcc().getNickname(FirebaseAuth.getInstance().currentUser?.email.toString(),
                view.findViewById<EditText>(R.id.postfragmentsubject).text.toString(),
                view.findViewById<EditText>(R.id.postfragmenttext).text.toString(),
                view)
            dismiss()
        }

        return view
    }
}