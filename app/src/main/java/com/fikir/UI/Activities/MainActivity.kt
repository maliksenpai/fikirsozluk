package com.fikir.UI.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Firebase.MainMenu
import com.fikir.Model.Firebase.SearchName
import com.fikir.Model.Firebase.Status
import com.fikir.Presenter.MainPresenter
import com.fikir.R
import com.fikir.UI.Fragments.NewPostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val presenter: MainPresenter =MainPresenter()
        super.onCreate(savedInstanceState)
        Status().setonline(FirebaseAuth.getInstance().currentUser?.email.toString())
        setContentView(R.layout.activity_main2)
        val bar=supportActionBar
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
        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menuanasayfa -> {
                    findViewById<RelativeLayout>(R.id.anasayfa).visibility=View.VISIBLE
                    findViewById<RelativeLayout>(R.id.profil).visibility=View.INVISIBLE
                    findViewById<RelativeLayout>(R.id.arama).visibility=View.INVISIBLE
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menuprofil -> {
                    findViewById<RelativeLayout>(R.id.anasayfa).visibility=View.INVISIBLE
                    findViewById<RelativeLayout>(R.id.profil).visibility=View.VISIBLE
                    findViewById<RelativeLayout>(R.id.arama).visibility=View.INVISIBLE
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menuara -> {
                    findViewById<RelativeLayout>(R.id.anasayfa).visibility=View.INVISIBLE
                    findViewById<RelativeLayout>(R.id.profil).visibility=View.INVISIBLE
                    findViewById<RelativeLayout>(R.id.arama).visibility=View.VISIBLE
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        menunavigationmenu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        MainMenu().profildetails(this)
        findViewById<EditText>(R.id.aramatext).addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                SearchName().names(findViewById<RecyclerView>(R.id.aramalist),s.toString(), s.toString().length,this@MainActivity)
            }

            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

        })
    }
    fun showpostfragmentmain(){
        val fragment:NewPostFragment= NewPostFragment()
        fragment.show(supportFragmentManager,"postfragment")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.maintop,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.cikis -> {
                Status().setoffline(FirebaseAuth.getInstance().currentUser?.email.toString())
                MainMenu().exitaccount()
                startActivity(Intent(applicationContext,LoginActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
