package com.leodeleon.data.entities

import com.leodeleon.domain.entities.FeedPhoto

data class FeedPhotoEntity(
        val author: String,
        val media: MediaEntity) {

    fun unwrap() = FeedPhoto(
            media.m.replace("_m.","_c."),
            author
    )
}

data class MediaEntity(val m: String)