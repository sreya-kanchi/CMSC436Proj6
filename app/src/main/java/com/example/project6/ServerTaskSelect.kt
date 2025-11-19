package com.example.project6

import android.util.Log
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class ServerTaskSelect(private val activity: MainActivity) {

    fun start() {
        Thread {
            try {
                val url = URL(MainActivity.URL_ALL)
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"
                conn.connectTimeout = 5000
                conn.readTimeout = 5000

                val response = conn.inputStream.bufferedReader().use { it.readText() }
                conn.disconnect()

                val candies = ArrayList<Candy>()
                val jsonArray = JSONArray(response)
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val id = obj.getInt("id")
                    val name = obj.getString("name")
                    val price = obj.getDouble("price")
                    candies.add(Candy(id, name, price))
                }

                activity.runOnUiThread {
                    activity.onCandiesLoaded(candies)
                }
            } catch (e: Exception) {
                Log.e("ServerTaskSelect", "Error fetching candies", e)
            }
        }.start()
    }
}
