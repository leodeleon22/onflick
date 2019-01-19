package com.leodeleon.domain

import io.reactivex.Observable


class LoadFeed(private val repository: IFeedRepository) {

    operator fun invoke(): Observable<State> {
        return repository
                .getPublicFeed()
                .map<State> {
                    FeedLoaded(it)
                }
                .onErrorReturn { Error }
                .startWith(Loading)
    }

}