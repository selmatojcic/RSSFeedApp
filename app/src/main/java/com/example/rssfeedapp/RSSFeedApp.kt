package com.example.rssfeedapp

import android.app.Application

class RSSFeedApp : Application() {

    companion object {
        lateinit var application: Application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}