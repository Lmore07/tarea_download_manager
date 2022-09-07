package com.example.download_manager

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class MainActivity4 : AppCompatActivity() {

    var downloadid: Long = 0
    lateinit var txt: TextView
    lateinit var adminPermisos: Administrador_Permisos
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter3.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        //PERMISOS_SOLICITADOS
        adminPermisos = Administrador_Permisos(this@MainActivity4)

        val permisosSolicitados = ArrayList<String?>()
        permisosSolicitados.add(Manifest.permission.CAMERA)
        permisosSolicitados.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        permisosSolicitados.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        permisosSolicitados.add(Manifest.permission.WRITE_CALENDAR)

        val permisosAprobados:ArrayList<String?>   = adminPermisos.getPermisosAprobados(permisosSolicitados)
        val listPermisosNOAprob:ArrayList<String?> = adminPermisos.getPermisosNoAprobados(permisosSolicitados)

        adminPermisos.getPermission(listPermisosNOAprob)

        val bundle = this.getIntent().getExtras();
        var id=bundle?.getString("id")
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView3)
        recyclerView.layoutManager = layoutManager

        val queue = Volley.newRequestQueue(this)
        val stringRequest = JsonArrayRequest(
            Request.Method.GET, "https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+id,null,
            { response ->
                var lstpubs = ArrayList<Publicacion>()
                lstpubs = Publicacion.JsonObjectsBuild(response)

                val resId = R.anim.layout_animation_down_to_up
                val animation = AnimationUtils.loadLayoutAnimation(
                    applicationContext,
                    resId
                )
                recyclerView.layoutAnimation = animation

                adapter = RecyclerAdapter3(lstpubs,manager)

                recyclerView.adapter = adapter

            },{
            })


        queue.add(stringRequest)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var resp:String  = adminPermisos.onRequestPermissionsResult(requestCode, permissions as Array<String>, grantResults)
    }


}