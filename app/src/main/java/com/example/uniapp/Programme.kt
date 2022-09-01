package com.example.uniapp

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Programme(jsonObject: JSONObject) {
    lateinit var programmeName: String
    lateinit var faculty: String

    companion object {
        // Factory method to convert an array of JSON objects into a list of objects
        // Programme.fromJson(jsonArray);
        fun fromJson(jsonObjects: JSONArray): ArrayList<Programme> {
            val programmesList = ArrayList<Programme>()
            for (i in 0 until jsonObjects.length()) {
                try {
                    programmesList.add(Programme(jsonObjects.getJSONObject(i)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                    continue
                }
            }
            return programmesList
        }
    }

    // Constructor to convert JSON object into a Java class instance
    init {
        try {
            this.programmeName = jsonObject.getString("programmeName")
            this.faculty = jsonObject.getString("faculty")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}