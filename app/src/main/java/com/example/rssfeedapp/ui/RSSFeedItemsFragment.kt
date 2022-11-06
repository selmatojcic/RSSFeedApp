package com.example.rssfeedapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssfeedapp.adapters.RSSFeedItemAdapter
import com.example.rssfeedapp.databinding.FragmentRssFeedItemsBinding
import com.example.rssfeedapp.listeners.OnRSSFeedItemClickedListener
import com.example.rssfeedapp.model.RSSFeed
import com.example.rssfeedapp.viewmodel.RSSFeedViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RSSFeedItemsFragment : Fragment() {
    private lateinit var rssFeedItemsFeedsBinding: FragmentRssFeedItemsBinding
    private val rssFeedViewModel by viewModel<RSSFeedViewModel>()
    private lateinit var rssFeedItemClickedListener: OnRSSFeedItemClickedListener
    private lateinit var rssFeedItemAdapter: RSSFeedItemAdapter

    companion object {
        const val TAG = "RSSFeedItemsFragment"
        private const val KEY = "RSSFeedItem"

        fun create(rssFeed: RSSFeed): RSSFeedItemsFragment {
            val args = Bundle()
            args.putSerializable(KEY, rssFeed)
            val fragment = RSSFeedItemsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rssFeedItemsFeedsBinding = FragmentRssFeedItemsBinding.inflate(
            inflater,
            container,
            false
        )

        rssFeedItemAdapter = RSSFeedItemAdapter(emptyList(), rssFeedItemClickedListener)

        viewLifecycleOwner.lifecycleScope.launch {
            arguments?.let {
                val rssFeed = it.getSerializable(KEY) as RSSFeed
                rssFeedViewModel.loadRSSFeedItems(rssFeed.rssFeedURL)
                setupRecyclerView(rssFeed.rssFeedURL)
            }
        }

        return rssFeedItemsFeedsBinding.root
    }

    private suspend fun setupRecyclerView(url: String) {
        rssFeedItemsFeedsBinding.itemsRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        rssFeedItemsFeedsBinding.itemsRecyclerView.adapter =
            RSSFeedItemAdapter(
                rssFeedViewModel.loadRSSFeedItems(url),
                rssFeedItemClickedListener
            )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRSSFeedItemClickedListener) {
            rssFeedItemClickedListener = context
        }
    }
}