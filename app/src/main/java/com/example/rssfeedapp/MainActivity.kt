package com.example.rssfeedapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rssfeedapp.databinding.ActivityMainBinding
import com.example.rssfeedapp.model.Image
import com.example.rssfeedapp.model.RSS
import com.example.rssfeedapp.model.RSSFeed
import com.example.rssfeedapp.viewmodel.RSSFeedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnRSSFeedSelectedListener {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var rssFeedViewModel: RSSFeedViewModel

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        rssFeedViewModel = ViewModelProvider(this)[RSSFeedViewModel::class.java]

        editText = findViewById(R.id.addLinkTextInputEditText)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragmentContainer,
                    RSSFeedsFragment.create(),
                    RSSFeedsFragment.TAG
                )
                .commit()
        }
    }

    fun requestCall(view: View) {
        val api = RSSFeedApi.create()
        val call = api.getRSS(editText.text.toString())

        call.enqueue(object : Callback<RSS> {
            override fun onResponse(call: Call<RSS>, response: Response<RSS>) {
                response.body()?.channel?.let {
                    addRSSFeed(
                        it.title,
                        it.description,
                        it.image,
                        editText.text.toString()
                    )
                }
            }

            override fun onFailure(call: Call<RSS>, t: Throwable) {
                Log.e("TAG", "Failure" + t.message)
            }
        })
    }

    fun addRSSFeed(title: String, description: String, image: Image, rssFeedURL: String) {
        val rssFeed = RSSFeed(0, title, description, image.url, rssFeedURL)
        rssFeedViewModel.insertRSSFeed(rssFeed)
    }

    override fun onRSSFeedSelected(rssFeed: RSSFeed) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,
                RSSFeedItemsFragment.create(rssFeed),
                RSSFeedItemsFragment.TAG)
            .addToBackStack(null)
            .commit()
    }
}