package com.fikir.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.fikir.Presenter.ReadPresenter
import com.fikir.R
import com.fikir.UI.Activities.ReadPost
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class ReadPostNewPost: DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.readpostnewpostfragment,null)
        return view
    }

}