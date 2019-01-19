package com.leodeleon.onflick.di

import com.leodeleon.data.*
import com.leodeleon.data.remote.FlickrApi
import com.leodeleon.domain.LoadFeed
import com.leodeleon.domain.IFeedRepository
import com.leodeleon.domain.RefreshFeed
import com.leodeleon.onflick.feed.FeedViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { FeedViewModel(get(), get(), get()) }
    single { SchedulerProvider() as SchedulerFactory }
    single { FlickrApi(get()).service }
    single { LoadFeed(get()) }
    single { RefreshFeed(get()) }
    single { FeedRepository(get()) as IFeedRepository }
}