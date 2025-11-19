package com.example.project6

import android.util.Log
import java.net.HttpURLConnection
import java.net.URLEncoder
import java.net.URL

class ServerTaskInsert(
    private val activity: InsertActivity,
    private val name: String,
    private val price: Double
) {

    fun start() {
        Thread {
            try {
                val encodedName = URLEncoder.encode(name, "UTF-8")
                val encodedPrice = URLEncoder.encode(price.toString(), "UTF-8")

                val fullUrl = "${MainActivity.URL_INSERT}?id=-1&name=$encodedName&price=$encodedPrice"
                val url = URL(fullUrl)
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"
                conn.connectTimeout = 5000
                conn.readTimeout = 5000

                conn.inputStream.use { /* ignore body */ }
                conn.disconnect()
            } catch (e: Exception) {
                Log.e("ServerTaskInsert", "Error inserting candy", e)
            }

            activity.runOnUiThread {
                // go back to MainActivity; it will refresh in onResume()
                activity.finish()
            }
        }.start()
    }
}
