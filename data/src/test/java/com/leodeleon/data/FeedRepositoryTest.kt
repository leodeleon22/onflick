package com.leodeleon.data

import com.leodeleon.domain.Feed
import com.leodeleon.domain.FeedPhoto
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.Single
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class FeedRepositoryTest {
    @MockK
    private lateinit var service: FlickrService

    private val repo: FeedRepository by lazy {
        FeedRepository(service)
    }

    @Test
    fun `when get feed then return success`() {
        val feed = Feed(
                title = "test",
                items = listOf(
                        FeedPhoto("a","a"),
                        FeedPhoto("b","b")
                )
        )

        success(feed)

        repo.getPublicFeed().test().apply {
            assertNoErrors()
            assertComplete()
            assertValues(feed)
        }
    }

    private fun success(feed: Feed) {
        every {
            service.getPublicFeed()
        }.returns(Single.just(feed))
    }
}