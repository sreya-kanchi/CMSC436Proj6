package com.example.project6

import android.util.Log
import java.net.HttpURLConnection
import java.net.URL

class ServerTaskDelete(
    private val activity: DeleteActivity,
    private val candyId: Int,
    private val onSuccess: () -> Unit
) {

    fun start() {
        Thread {
            try {
                val fullUrl = "${MainActivity.URL_DELETE}?id=$candyId"
                val url = URL(fullUrl)
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"
                conn.connectTimeout = 5000
                conn.readTimeout = 5000

                conn.inputStream.use {  }
                conn.disconnect()

                activity.runOnUiThread {
                    onSuccess()
                }
            } catch (e: Exception) {
                Log.e("ServerTaskDelete", "Error deleting candy", e)
            }
        }.start()
    }
}
