package com.leodeleon.data

import com.leodeleon.domain.IFeedRepository

class FeedRepository(private val service: FlickrService): IFeedRepository {
    override fun getPublicFeed() = service.getPublicFeed()
}