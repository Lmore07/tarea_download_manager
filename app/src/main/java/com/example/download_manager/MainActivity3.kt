package com.example.download_manager

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class MainActivity3 : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val bundle = this.getIntent().getExtras();
        var id=bundle?.getString("id")
        layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerView.layoutManager = layoutManager

        val queue = Volley.newRequestQueue(this)
        val stringRequest = JsonArrayRequest(
            Request.Method.GET, "https://revistas.uteq.edu.ec/ws/issues.php?j_id="+id,null,
            { response ->
                var lstvolumenes = ArrayList<Volumen>()
                lstvolumenes = Volumen.JsonObjectsBuild(response)

                val resId = R.anim.layout_animation_down_to_up
                val animation = AnimationUtils.loadLayoutAnimation(
                    applicationContext,
                    resId
                )
                recyclerView.layoutAnimation = animation

                adapter = RecyclerAdapter2(lstvolumenes)

                recyclerView.adapter = adapter

            },{
                var i="a";
            })


        queue.add(stringRequest)
    }



}