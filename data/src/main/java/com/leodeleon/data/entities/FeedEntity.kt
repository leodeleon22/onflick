package com.leodeleon.data.entities

import com.leodeleon.domain.entities.Feed

data class FeedEntity(
        val title: String,
        val items: List<FeedPhotoEntity>
) {

    fun unwrap() = Feed(title, items.map { it.unwrap() })
}