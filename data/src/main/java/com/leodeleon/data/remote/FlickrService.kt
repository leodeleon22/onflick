package com.leodeleon.data.remote

import com.leodeleon.data.entities.FeedEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface FlickrService {

    @GET("/services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    fun getPublicFeed(): Observable<FeedEntity>

}