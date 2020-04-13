package com.fikir.Model.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fikir.Model.Module.ChatModule
import com.fikir.Model.Module.ReadModule
import com.fikir.R

class ChatAdapter(val liste:MutableList<ChatModule>): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_2,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nick.setText(liste[position].nick)
        holder.text.setText(liste[position].text)
    }

    fun cleardata(){
        liste.clear()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nick=itemView.findViewById<TextView>(R.id.chatlistnick)
        val text=itemView.findViewById<TextView>(R.id.chatlisttext)

    }
}