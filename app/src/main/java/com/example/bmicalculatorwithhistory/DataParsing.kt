package com.example.bmicalculatorwithhistory

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class DataParsing {
    private fun getPlace(googlePlaceJson: JSONObject): HashMap<String, String> {
        val googlePlaceMap = HashMap<String, String>()
        var placeName = "--NA--"
        var vicinity = "--NA--"
        var latitude = ""
        var longitude = ""
        var reference = ""
        Log.d("DataParser", "jsonobject =$googlePlaceJson")
        try {
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name")
            }
            if (!googlePlaceJson.isNull("vicinity")) {
                vicinity = googlePlaceJson.getString("vicinity")
            }
            latitude =
                googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat")
            longitude =
                googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng")
            reference = googlePlaceJson.getString("reference")
            googlePlaceMap["place_name"] = placeName
            googlePlaceMap["vicinity"] = vicinity
            googlePlaceMap["lat"] = latitude
            googlePlaceMap["lng"] = longitude
            googlePlaceMap["reference"] = reference
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return googlePlaceMap
    }

    private fun getPlaces(jsonArray: JSONArray?): List<HashMap<String, String>?> {
        val count = jsonArray!!.length()
        val placelist: MutableList<HashMap<String, String>?> = ArrayList()
        var placeMap: HashMap<String, String>? = null
        for (i in 0 until count) {
            try {
                placeMap = getPlace(jsonArray[i] as JSONObject)
                placelist.add(placeMap)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return placelist
    }

    fun parse(jsonData: String?): List<HashMap<String, String>?> {
        var jsonArray: JSONArray? = null
        val jsonObject: JSONObject
        Log.d("json data", jsonData!!)
        try {
            jsonObject = JSONObject(jsonData)
            jsonArray = jsonObject.getJSONArray("results")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return getPlaces(jsonArray)
    }
}