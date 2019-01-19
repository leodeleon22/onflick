package com.leodeleon.data

import com.leodeleon.data.entities.FeedEntity
import com.leodeleon.data.entities.FeedPhotoEntity
import com.leodeleon.data.entities.MediaEntity
import com.leodeleon.data.remote.FlickrService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.Observable
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
        val feed = FeedEntity(
                title = "test",
                items = listOf(
                        FeedPhotoEntity("a", MediaEntity("a")),
                        FeedPhotoEntity("b", MediaEntity("b"))
                )
        )

        success(feed)

        repo.getPublicFeed().test().apply {
            assertNoErrors()
            assertComplete()
            assertValues(feed.unwrap())
        }
    }

    private fun success(feed: FeedEntity) {
        every {
            service.getPublicFeed()
        }.returns(Observable.just(feed))
    }
}