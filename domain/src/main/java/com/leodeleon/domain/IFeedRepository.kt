package com.leodeleon.domain

import io.reactivex.Single

interface IFeedRepository {
    fun getPublicFeed(): Single<Feed>
}