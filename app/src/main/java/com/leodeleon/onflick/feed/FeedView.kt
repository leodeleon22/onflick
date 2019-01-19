package com.leodeleon.onflick.feed

import com.leodeleon.domain.State

interface FeedView {
    fun loadFeed()
    fun render(state: State)
}