package com.leodeleon.data

import com.leodeleon.domain.Feed
import io.reactivex.Single
import retrofit2.http.GET

interface FlickrService {

    @GET("/services/feeds/photos_public.gne?format=json")
    fun getPublicFeed(): Single<Feed>

}