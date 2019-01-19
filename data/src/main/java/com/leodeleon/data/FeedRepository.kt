package com.leodeleon.data

import com.leodeleon.data.remote.FlickrService
import com.leodeleon.domain.IFeedRepository

class FeedRepository(private val service: FlickrService): IFeedRepository {
    override fun getPublicFeed() = service.getPublicFeed().map { it.unwrap() }
}