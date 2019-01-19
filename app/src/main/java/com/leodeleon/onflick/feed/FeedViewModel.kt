package com.leodeleon.onflick.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leodeleon.data.SchedulerFactory
import com.leodeleon.domain.entities.Feed
import com.leodeleon.domain.LoadFeed
import com.leodeleon.domain.RefreshFeed
import com.leodeleon.domain.State
import com.leodeleon.onflick.BR
import com.leodeleon.onflick.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList

class FeedViewModel(
        private val schedulers: SchedulerFactory,
        private val loadFeed: LoadFeed,
        private val refreshFeed: RefreshFeed
) : ViewModel() {

    private val subscriptions = CompositeDisposable()
    private val liveState = MutableLiveData<State>()
    val state: LiveData<State> = liveState

    val itemBinding: ItemBinding<FeedItemViewData> = ItemBinding.of(BR.viewData, R.layout.item_photo)
    val items: DiffObservableList<FeedItemViewData> = DiffObservableList(object : DiffObservableList.Callback<FeedItemViewData>{
        override fun areItemsTheSame(oldItem: FeedItemViewData, newItem: FeedItemViewData): Boolean {
            return oldItem.image_url != newItem.image_url
        }

        override fun areContentsTheSame(oldItem: FeedItemViewData, newItem: FeedItemViewData) = false
    })

    fun updateItems(feed: Feed){
        val newItems = feed.items.map { FeedItemViewData(it) }
        items.update(newItems)
    }

    fun loadData(){
        loadFeed().observeOn(schedulers.main())
                .subscribeBy {
                    liveState.postValue(it)
                }
                .addTo(subscriptions)
    }

    fun refresh(){
        refreshFeed().observeOn(schedulers.main())
                .subscribe {
                    liveState.postValue(it)
                }.addTo(subscriptions)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}