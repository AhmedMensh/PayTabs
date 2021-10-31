package com.android.paytabs.network

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL


object QueryUtils {


    fun init(url: URL): String {
        var jsonResponse = ""

        var urlConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        try {
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 60000
            urlConnection.connectTimeout = 60000
            urlConnection.connect()
            Log.d("TAG", "init: ${urlConnection.responseCode}")
            Log.d("TAG", "init: ${urlConnection.responseMessage}")
            if (urlConnection.responseCode == 200) {
                inputStream = urlConnection.inputStream
                jsonResponse = readFromStream(inputStream) ?: ""
            } else {
                return urlConnection.responseMessage
            }
        } catch (e: Exception) {

            return e.localizedMessage ?: "Something went wrong."

        } finally {
            urlConnection?.disconnect()
            inputStream?.close()
        }
        return jsonResponse
    }

    @Throws(IOException::class)
    private fun readFromStream(inputStream: InputStream?): String? {
        var jsonResponse = ""
        val inputStreamReader: InputStreamReader
        val bufferedReader: BufferedReader
        if (inputStream != null) {
            inputStreamReader = InputStreamReader(inputStream)
            bufferedReader = BufferedReader(inputStreamReader)
            val output = StringBuilder()
            try {
                var line = bufferedReader.readLine()
                while (line != null) {
                    output.append(line)
                    line = bufferedReader.readLine()
                }
                jsonResponse = output.toString()
            } catch (e: IOException) {
                Log.e("LOG_TAG", "Error while reading String from inputStream", e)
            } finally {
                try {
                    inputStreamReader.close()
                    bufferedReader.close()
                } catch (e: IOException) {
                    Log.e("LOG_TAG", "Error while closing inputStreamReader and bufferedReader")
                }
            }
        }
        return jsonResponse

    }
}