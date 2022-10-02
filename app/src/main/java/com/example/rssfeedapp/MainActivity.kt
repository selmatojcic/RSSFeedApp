package com.example.rssfeedapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rssfeedapp.model.Channel
import com.example.rssfeedapp.model.RSS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = RSSFeedApi.create()
        val call = api.getRSS()

        call.enqueue(object: Callback<RSS> {
            override fun onResponse(call: Call<RSS>, response: Response<RSS>) {
                Log.e("TAG", "Success" + response.body().toString())
                Log.e("TAG", "Server response$response")
            }

            override fun onFailure(call: Call<RSS>, t: Throwable) {
                Log.e("TAG", "Failure" + t.message)
            }

        })
    }
}