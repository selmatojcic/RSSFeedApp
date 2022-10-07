package com.example.rssfeedapp.networking

import com.example.rssfeedapp.model.RSS
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface RSSFeedApi {

    @GET
    fun getRSS(@Url url: String): Call<RSS>

    companion object {
        fun create(): RSSFeedApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://feeds.bbci.co.uk/news/world/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
            return retrofit.create(RSSFeedApi::class.java)

        }
    }
}