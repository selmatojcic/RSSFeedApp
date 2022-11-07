package com.example.rssfeedapp

import android.app.Application
import com.example.rssfeedapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RSSFeedApp : Application() {
    companion object {
        lateinit var application: Application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        startKoin {
            androidContext(this@RSSFeedApp)
            modules(viewModelModule)
        }
    }
}