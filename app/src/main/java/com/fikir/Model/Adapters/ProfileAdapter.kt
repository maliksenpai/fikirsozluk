package com.fikir.Model.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fikir.R
import com.fikir.UI.Activities.ReadPost

class ProfileAdapter (val liste:MutableList<String>): RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_1,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.subject.setText(liste[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subject=itemView.findViewById<TextView>(R.id.profilelisttext)
    }
}