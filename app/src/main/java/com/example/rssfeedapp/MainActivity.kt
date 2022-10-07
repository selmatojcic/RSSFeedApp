package com.example.rssfeedapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rssfeedapp.databinding.ActivityMainBinding
import com.example.rssfeedapp.model.Item
import com.example.rssfeedapp.model.RSSFeed


class MainActivity : AppCompatActivity(), OnRSSFeedClickedListener, OnRSSFeedItemClickedListener {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

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

    override fun onRSSFeedClicked(rssFeed: RSSFeed) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                RSSFeedItemsFragment.create(rssFeed),
                RSSFeedItemsFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onRSSFeedItemClicked(item: Item) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
        startActivity(browserIntent)
    }
}