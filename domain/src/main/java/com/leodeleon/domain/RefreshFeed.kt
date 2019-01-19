package com.leodeleon.domain

import io.reactivex.Observable

class RefreshFeed(private val repository: IFeedRepository) {

    operator fun invoke(): Observable<State> {
        return repository
                .getPublicFeed()
                .map<State> {
                    FeedLoaded(it)
                }
                .onErrorReturnItem(Refresh(false))
                .startWith(Refresh(true))
    }

}