package com.example.download_manager

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Volumen(a: JSONObject) {
    var nombres: String
    var publicacion: String
    var id: String
    var urlavatar: String
    var doi:String

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): ArrayList<Volumen> {
            val volumen: ArrayList<Volumen> = ArrayList<Volumen>()
            var i = 0
            while (i < datos.length()) {
                volumen.add(Volumen(datos.getJSONObject(i)))
                i++
            }
            return volumen
        }
    }

    init {
        nombres = "Vol. "+ a.getString("volume").toString()+ " Núm. "+a.getString("number").toString() +": "+a.getString("title").toString()
        publicacion = a.getString("date_published").toString()
        id = a.getString("issue_id").toString()
        urlavatar = a.getString("cover").toString()
        doi=a.getString("doi").toString()
    }
}