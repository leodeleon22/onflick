package com.leodeleon.data.remote

import com.leodeleon.data.SchedulerFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class FlickrApi(val schedulers: SchedulerFactory) {
    private val okhttpClient = provideOkHttpClient()

    val service: FlickrService = provideService(okhttpClient)

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .build()
    }

    private inline fun <reified T> provideService(okHttp: OkHttpClient): T {
        val retrofit =
                Retrofit.Builder()
                        .client(okHttp)
                        .addConverterFactory(MoshiConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulers.io()))
                        .baseUrl(BASE_URL)
                        .build()

        return retrofit.create(T::class.java)
    }

    private companion object {
        const val BASE_URL = "https://api.flickr.com"
    }

}