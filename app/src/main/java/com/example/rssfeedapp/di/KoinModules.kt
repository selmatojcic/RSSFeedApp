package com.example.rssfeedapp.di

import com.example.rssfeedapp.data.RSSFeedRepository
import com.example.rssfeedapp.viewmodel.RSSFeedViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RSSFeedViewModel(androidApplication()) }

    single { RSSFeedRepository(get()) }
}