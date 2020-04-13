package com.fikir.Model.Adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Module.SearchModule
import com.fikir.R
import com.fikir.UI.Activities.SearchProfileActivity

class SearchAdapter(val liste:MutableList<SearchModule>): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_3,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    fun cleardata(){
        liste.clear()
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.usernick.setText(liste[position].nick)
        if(liste[position].status=="0"){
            holder.userstatus.setBackgroundColor(R.color.offlinestatus)
        }
        else{
            holder.userstatus.setBackgroundColor(R.color.onlinestatus)
        }
        holder.usernick.setOnClickListener {
            var intent = Intent(holder.itemView.context,SearchProfileActivity::class.java)
            intent.putExtra("nickname",liste[position].nick)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernick=itemView.findViewById<TextView>(R.id.listusersnick)
        var userstatus=itemView.findViewById<ImageView>(R.id.listusersstatus)
    }
}