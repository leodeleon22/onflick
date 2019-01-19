package com.leodeleon.domain

import com.leodeleon.domain.entities.Feed
import io.reactivex.Observable
import io.reactivex.Single

interface IFeedRepository {
    fun getPublicFeed(): Observable<Feed>
}