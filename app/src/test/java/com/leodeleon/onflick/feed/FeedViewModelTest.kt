package com.leodeleon.onflick.feed

import com.jraska.livedata.TestObserver
import com.leodeleon.domain.*
import com.leodeleon.domain.entities.Feed
import com.leodeleon.domain.entities.FeedPhoto
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.Observable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class,InstantExecutorExtension::class)
internal class FeedViewModelTest {
    @MockK
    private lateinit var repository: IFeedRepository
    private lateinit var  viewModel: FeedViewModel
    private val feed = Feed("a",
            listOf(FeedPhoto("a","aaa"),
                    FeedPhoto("b","bbb")))

    @BeforeEach
    fun setup(){
        val loadFeed = LoadFeed(repository)
       val refreshFeed = RefreshFeed(repository)
        val schedulers = TestSchedulers()
        viewModel = FeedViewModel(schedulers, loadFeed, refreshFeed)
    }

    @Test
    fun `when load data then return loaded state`() {
        success()
        val observer = TestObserver.test(viewModel.state)
        viewModel.loadData()
        observer.assertHistorySize(2)
        assertEquals(listOf(Loading, FeedLoaded(feed)), observer.valueHistory())
    }

    @Test
    fun `when error then return error state`() {
        error()
        val observer = TestObserver.test(viewModel.state)
        viewModel.loadData()
        observer.assertHistorySize(2)
        assertEquals(listOf(Loading, Error), observer.valueHistory())
    }

    @Test
    fun `when refresh then return loaded state`() {
        success()
        val observer = TestObserver.test(viewModel.state)
        viewModel.refresh()
        observer.assertHistorySize(2)
        assertEquals(listOf(Refresh(true), FeedLoaded(feed)), observer.valueHistory())
    }

    @Test
    fun `when refresh error then return refresh state`() {
        error()
        val observer = TestObserver.test(viewModel.state)
        viewModel.refresh()
        observer.assertHistorySize(2)
        assertEquals(listOf(Refresh(true), Refresh(false)), observer.valueHistory())
    }

    private fun success() {
        every {
            repository.getPublicFeed()
        }.returns(Observable.just(feed))
    }

    private fun error() {
        every {
            repository.getPublicFeed()
        }.returns(Observable.fromCallable { throw  Exception("error")})
    }
}