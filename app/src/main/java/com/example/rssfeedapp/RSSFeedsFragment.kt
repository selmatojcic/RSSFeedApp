package com.example.rssfeedapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedapp.adapters.RSSFeedAdapter
import com.example.rssfeedapp.databinding.FragmentRssFeedsBinding
import com.example.rssfeedapp.viewmodel.RSSFeedViewModel

class RSSFeedsFragment : Fragment() {
    private lateinit var fragmentRssFeedsBinding: FragmentRssFeedsBinding
    private lateinit var rssFeedViewModel: RSSFeedViewModel
    private lateinit var onRSSFeedSelectedListener: OnRSSFeedSelectedListener
    private lateinit var rssFeedAdapter: RSSFeedAdapter

    companion object {
        const val TAG = "RSS FEEDS"
        fun create(): RSSFeedsFragment {
            return RSSFeedsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentRssFeedsBinding = FragmentRssFeedsBinding.inflate(
            inflater,
            container,
            false
        )
        rssFeedViewModel = ViewModelProvider(this)[RSSFeedViewModel::class.java]

        rssFeedAdapter = RSSFeedAdapter(onRSSFeedSelectedListener)

        setupRecyclerView()

        return fragmentRssFeedsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rssFeedViewModel.getRSSFeeds().observe(
            viewLifecycleOwner
        ) { feeds -> rssFeedAdapter.refreshData(feeds) }

        rssFeedViewModel.getRSSFeeds()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnRSSFeedSelectedListener){
            onRSSFeedSelectedListener = context
        }
    }

    private fun setupRecyclerView() {
        fragmentRssFeedsBinding.apply {
            feedsRecyclerView.layoutManager = LinearLayoutManager(
                this@RSSFeedsFragment.context,
                RecyclerView.VERTICAL,
                false
            )
            this@RSSFeedsFragment.rssFeedAdapter.setHasStableIds(true)
            feedsRecyclerView.adapter = this@RSSFeedsFragment.rssFeedAdapter
        }
    }
}