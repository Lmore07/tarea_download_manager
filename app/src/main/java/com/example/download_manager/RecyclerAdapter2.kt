package com.example.download_manager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class RecyclerAdapter2 (val userList: ArrayList<Volumen>) : RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.lista_volumenes, viewGroup, false)
        return ViewHolder(v)
    }

    private lateinit var contexto: Context

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var txtNombre: TextView
        var txt_doi: TextView
        var txt_fecha:TextView

        init {
            txtNombre = itemView.findViewById(R.id.txt_nombre)
            txt_doi = itemView.findViewById(R.id.txt_doi)
            itemImage = itemView.findViewById(R.id.imagen2)
            txt_fecha=itemView.findViewById(R.id.fecha)
            itemView.setOnClickListener { v: View ->
                var position: Int = getAdapterPosition()
                val intent= Intent(contexto, MainActivity4::class.java)
                val b = Bundle();
                b.putString("id", userList[position].id);
                intent.putExtras(b);
                ContextCompat.startActivity(contexto, intent, b)
                Snackbar.make(v, "Item Selecccionado $position",
                    Snackbar.LENGTH_LONG).setAction("Actción", null).show()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerAdapter2.ViewHolder, position: Int) {
        contexto=holder.itemView.context;
        holder.txtNombre.text = userList[position].nombres
        holder.txt_doi.text = userList[position].doi
        holder.txt_fecha.text = userList[position].publicacion
        Picasso.get().load(userList[position].urlavatar).into(holder.itemImage);
    }
}