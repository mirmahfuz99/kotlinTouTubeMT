package io.mir.kotlintoutubemt

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        fetchJson()
    }
    fun fetchJson() {
        println("Attempting to fetch JSON")
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
//                println(body)
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body,HomeFeed::class.java)
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(homeFeed)

                }
            }
        })
    }
}
