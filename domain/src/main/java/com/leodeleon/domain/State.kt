package com.leodeleon.domain

import com.leodeleon.domain.entities.Feed

sealed class State
object Loading: State()
object Error: State()
data class Refresh(val isRefreshing: Boolean): State()
data class FeedLoaded(val feed: Feed): State()
