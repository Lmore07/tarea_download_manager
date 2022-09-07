package com.example.download_manager

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Revista(a: JSONObject) {
    var nombres: String
    var abreviacion: String
    var id: String
    var urlavatar: String

    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): ArrayList<Revista> {
            val revistas: ArrayList<Revista> = ArrayList<Revista>()
            var i = 0
            while (i < datos.length()) {
                revistas.add(Revista(datos.getJSONObject(i)))
                i++
            }
            return revistas
        }
    }

    init {
        nombres = a.getString("name").toString()
        abreviacion = a.getString("abbreviation").toString()
        id = a.getString("journal_id").toString()
        urlavatar = a.getString("portada").toString()
    }
}