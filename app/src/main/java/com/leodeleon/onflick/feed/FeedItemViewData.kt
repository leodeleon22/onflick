package com.leodeleon.onflick.feed

import com.leodeleon.domain.entities.FeedPhoto

class FeedItemViewData(item: FeedPhoto) {
    val image_url = item.image_url
    val username = item.author.let { it.split("\"")[1] }
}