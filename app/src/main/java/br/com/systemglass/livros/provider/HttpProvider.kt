package br.com.systemglass.livros.provider

import okhttp3.*
import java.io.IOException
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaType

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

    fun sendPost(url: String, json: String, callbackResponse: (String?) -> Unit) {
        val mediaTypeJson = "application/json; charset=utf-8".toMediaType()
        val request = Request.Builder()
            .url(url)
            .method("POST", json.toRequestBody(mediaTypeJson))
            .build()

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