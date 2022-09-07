package com.example.download_manager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class RecyclerAdapter (val userList: ArrayList<Revista>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.lista_revistas, viewGroup, false)
        return ViewHolder(v)
    }

    private lateinit var contexto:Context

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        contexto=holder.itemView.context;
        holder.txtNombre.text = userList[position].nombres
        holder.txt_abbre.text = userList[position].abreviacion
        Picasso.Builder(contexto).build().load(userList[position].urlavatar).into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var txtNombre: TextView
        var txt_abbre: TextView

        init {
            txtNombre = itemView.findViewById(R.id.txt_nombre)
            txt_abbre = itemView.findViewById(R.id.txt_doi)
            itemImage = itemView.findViewById(R.id.imagen)

            itemView.setOnClickListener { v: View  ->
                var position: Int = getAdapterPosition()
                val intent=Intent(contexto, MainActivity3::class.java)
                val b = Bundle();
                b.putString("id", userList[position].id);
                intent.putExtras(b);
                startActivity(contexto,intent,b)

            }
        }
    }
}