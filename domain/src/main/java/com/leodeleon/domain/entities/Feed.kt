package com.leodeleon.domain.entities

data class Feed(
        val title: String,
        val items: List<FeedPhoto>
)