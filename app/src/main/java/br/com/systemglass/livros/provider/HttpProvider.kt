package br.com.systemglass.livros.provider

import android.widget.Toast
import okhttp3.*
import java.io.IOException

class HttpProvider() {

    fun sendGet(url: String, callbackResponse: (String?) -> Unit) {
        val request = Request.Builder().url(url).build()

        val httpClient = OkHttpClient()
        httpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                callbackResponse(null)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                callbackResponse(body)
            }
        })
    }
}