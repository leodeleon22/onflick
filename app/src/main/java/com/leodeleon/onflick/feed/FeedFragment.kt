package com.leodeleon.onflick.feed


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.jakewharton.rxbinding3.view.clicks
import com.leodeleon.domain.FeedLoaded
import com.leodeleon.domain.Loading
import com.leodeleon.domain.Refresh
import com.leodeleon.domain.State

import com.leodeleon.onflick.databinding.FragmentFeedBinding
import com.leodeleon.onflick.utils.observe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_feed.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Error

class FeedFragment : Fragment() {
    private val feedViewModel: FeedViewModel by viewModel()
    private val subscriptions = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        binding.viewModel = feedViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(feedViewModel.state, ::render)

        refresh_layout.refreshes()
                .subscribe {
                    feedViewModel.refresh()
                }.addTo(subscriptions)

        toolbar_title.clicks()
                .subscribe {
                    recycler_feed.smoothScrollToPosition(0)
                }.addTo(subscriptions)

        feedViewModel.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscriptions.clear()
    }

    private fun render(state: State) {
        if(state !is Loading){
            progress.isVisible = false
        }

        if (state !is Error){
            error_group.isVisible = false
        }

        when(state){
            is Loading -> {
                progress.isVisible = true
                recycler_feed.isVisible = false
            }
            is Error -> {
                error_group.isVisible = true
                recycler_feed.isVisible = false
            }
            is Refresh -> {
                refresh_layout.isRefreshing = state.isRefreshing
            }
            is FeedLoaded -> {
                refresh_layout.isRefreshing = false
                recycler_feed.isVisible = true
                feedViewModel.updateItems(state.feed)
            }
        }
    }

}
