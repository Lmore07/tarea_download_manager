package com.example.download_manager

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter3(val userList: ArrayList<Publicacion>, val download_manager:DownloadManager) : RecyclerView.Adapter<RecyclerAdapter3.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.lista_publicaciones, viewGroup, false)
        return ViewHolder(v)
    }

    private lateinit var contexto: Context
    var downloadid: Long = 0
    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNombre: TextView
        var txt_fecha: TextView
        var imagen:ImageView
        init {
            txtNombre = itemView.findViewById(R.id.txt_nombre3)
            txt_fecha=itemView.findViewById(R.id.txt_fecha3)
            imagen=itemView.findViewById(R.id.imageView2)
            itemView.setOnClickListener { v: View ->
                var position: Int = getAdapterPosition()
                if (userList[position].linkHTML!=""){
                    val intent= Intent(contexto, MainActivity5::class.java)
                    val b = Bundle();
                    b.putString("link", userList[position].linkHTML);
                    intent.putExtras(b);
                    ContextCompat.startActivity(contexto, intent, b)
                }else{
                    Snackbar.make(v, "No existe elemento HTML",
                        Snackbar.LENGTH_LONG).setAction("Actción", null).show()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        contexto=holder.itemView.context;
        holder.txtNombre.text = userList[position].nombres
        holder.txt_fecha.text = userList[position].publicacion
        holder.imagen.setImageResource(userList[position].img)

        holder.imagen.setOnClickListener{v:View ->
            BajarDoc(userList[position].linkPDF,userList[position].nombres)
        };
    }

    fun BajarDoc(link:String, nombre:String) {
        val request =  DownloadManager.Request(Uri.parse(link))
            .setDescription("Download PDF")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            .setTitle(nombre)
            .setAllowedOverMetered(true)
            .setVisibleInDownloadsUi(true)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalFilesDir(this.contexto, Environment.DIRECTORY_DOWNLOADS,nombre+".pdf")
        try {
            downloadid = download_manager.enqueue(request)
            contexto.registerReceiver(MyBroadcastReceiver(downloadid), IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        } catch (e: Exception) {
            Toast.makeText(this.contexto, "Error: " + e.message, Toast.LENGTH_LONG).show()
        }
    }
}