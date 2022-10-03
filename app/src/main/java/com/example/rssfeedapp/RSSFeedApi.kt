package com.example.rssfeedapp

import com.example.rssfeedapp.model.RSS
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

interface RSSFeedApi {

    @GET("rss.xml")
    fun getRSS(): Call<RSS>

    companion object {
        var BASE_URL = "https://feeds.bbci.co.uk/news/world/"

        fun create(): RSSFeedApi {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
            return retrofit.create(RSSFeedApi::class.java)

        }
    }
}