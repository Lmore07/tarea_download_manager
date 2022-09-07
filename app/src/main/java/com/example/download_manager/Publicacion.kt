package com.example.download_manager

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Publicacion(a: JSONObject) {
    var nombres: String
    var publicacion: String
    var id: String
    var img: Int
    var linkHTML:String
    var linkPDF:String

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): ArrayList<Publicacion> {
            val publicacion: ArrayList<Publicacion> = ArrayList<Publicacion>()
            var i = 0
            while (i < datos.length()) {
                publicacion.add(Publicacion(datos.getJSONObject(i)))
                i++
            }
            return publicacion
        }
    }

    init {
        nombres = a.getString("title").toString()
        publicacion = a.getString("date_published").toString()
        id = a.getString("publication_id").toString()
        img=R.drawable.icono_download
        var asd=a.getJSONArray("galeys")
        linkHTML=""
        linkPDF=""
        for (i in 0 until asd.length()) {
            if(asd.getJSONObject(i).getString("label")=="PDF"){
                linkHTML=asd.getJSONObject(0).getString("UrlViewGalley")
                linkPDF=linkHTML.replace("view","download")+".pdf"
            }
        }
        var t=0
    }
}