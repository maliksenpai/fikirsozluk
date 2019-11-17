package com.fikir.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Presenter.MainPresenter
import com.fikir.R
import com.fikir.UI.Fragments.NewPostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.internal.NavigationMenuView
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDateTime

class Main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val presenter: MainPresenter =MainPresenter()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        presenter.init(this)
        presenter.giris()
        val auth:FirebaseAuth?=FirebaseAuth.getInstance()
        val menunavigationmenu:BottomNavigationView=findViewById(R.id.mainmenubnavigation)
        menunavigationmenu.selectedItemId=R.id.menuanasayfa
        findViewById<FloatingActionButton>(R.id.mainaddpost).setOnClickListener {
            Log.d("gelenmain","oldu")
            presenter.showpostfragmentpresenter()
        }
        presenter.postlist(findViewById<RecyclerView>(R.id.mainlist))
    }
    fun showpostfragmentmain(){
        val fragment:NewPostFragment= NewPostFragment()
        fragment.show(supportFragmentManager,"postfragment")
    }
}
