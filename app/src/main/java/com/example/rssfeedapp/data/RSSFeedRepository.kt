package com.example.rssfeedapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.rssfeedapp.model.Image
import com.example.rssfeedapp.model.RSS
import com.example.rssfeedapp.model.RSSFeed
import com.example.rssfeedapp.networking.RSSFeedApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RSSFeedRepository(private val rssFeedDao: RSSFeedDao) {
    fun getRSSFeeds(): LiveData<List<RSSFeed>> {
        return rssFeedDao.getRSSFeeds()
    }

    fun insertRSSFeed(rssFeed: RSSFeed) {
        CoroutineScope(IO).launch {
            rssFeedDao.insertRSSFeed(rssFeed)
        }
    }

    fun deleteRSSFeed(rssFeed: RSSFeed) {
        CoroutineScope(IO).launch {
            rssFeedDao.deleteRSSFeed(rssFeed)
        }
    }

    fun loadRSSFeeds(link: String) {
        val api = RSSFeedApi.create()
        val rssFeedLink = changeLinkProtocol(link)
        val call = api.getRSS(rssFeedLink)


        call.enqueue(object : Callback<RSS> {
            override fun onResponse(call: Call<RSS>, response: Response<RSS>) {
                response.body()?.channel?.let {
                    insertRSSFeed(
                        createRSSFeed(
                            it.title,
                            it.description,
                            it.image,
                            rssFeedLink
                        )
                    )
                }
            }

            override fun onFailure(call: Call<RSS>, t: Throwable) {
                Log.e("TAG", "Failure" + t.message)
            }
        })
    }

    fun createRSSFeed(
        title: String,
        description: String,
        image: Image,
        rssFeedURL: String
    ): RSSFeed {
        return RSSFeed(0, title, description, image.url, rssFeedURL)
    }

    private fun changeLinkProtocol(link: String): String {
        val oldPrefix = "http://"
        val newPrefix = "https://"
        var newLink = ""
        if (link.startsWith(oldPrefix)) {
            newLink = link.replace(oldPrefix, newPrefix)
            return newLink
        }
        return link
    }
}