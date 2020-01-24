package com.fikir.Model.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Module.ReadModule
import com.fikir.R

class ReadAdapter(val liste:MutableList<ReadModule>): RecyclerView.Adapter<ReadAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.postreadlist,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nick.setText(liste[position].nick)
        holder.text.setText(liste[position].text)
        holder.time.setText(liste[position].time)
    }

    fun cleardata(){
        liste.clear()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nick=itemView.findViewById<TextView>(R.id.postlistnick)
        val text=itemView.findViewById<TextView>(R.id.postlisttext)
        val time=itemView.findViewById<TextView>(R.id.postlisttime)
    }
}