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

class MainActivity2 : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager

        val queue = Volley.newRequestQueue(this)
        val stringRequest = JsonArrayRequest(
            Request.Method.GET, "https://revistas.uteq.edu.ec/ws/journals.php",null,
            { response ->
                var lsyRevista = ArrayList<Revista>()
                lsyRevista = Revista.JsonObjectsBuild(response)

                val resId = R.anim.layout_animation_down_to_up
                val animation = AnimationUtils.loadLayoutAnimation(
                    applicationContext,
                    resId
                )
                recyclerView.layoutAnimation = animation

                adapter = RecyclerAdapter(lsyRevista)

                recyclerView.adapter = adapter

            },{
                var a=0;
            })
        queue.add(stringRequest)
    }
}