package com.example.rssfeedapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedapp.adapters.RSSFeedAdapter
import com.example.rssfeedapp.databinding.FragmentRssFeedsBinding
import com.example.rssfeedapp.listeners.OnRSSFeedClickedListener
import com.example.rssfeedapp.viewmodel.RSSFeedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RSSFeedsFragment : Fragment() {
    private lateinit var fragmentRssFeedsBinding: FragmentRssFeedsBinding
    private val rssFeedViewModel by viewModel<RSSFeedViewModel>()
    private lateinit var onRSSFeedClickedListener: OnRSSFeedClickedListener
    private lateinit var rssFeedAdapter: RSSFeedAdapter

    companion object {
        const val TAG = "RSSFeedsFragment"
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

        rssFeedAdapter =
            context?.let { RSSFeedAdapter(onRSSFeedClickedListener, it, rssFeedViewModel) }!!

        fragmentRssFeedsBinding.addLinkButton.setOnClickListener {
            rssFeedViewModel
                .loadRSSFeeds(fragmentRssFeedsBinding.addLinkTextInputEditText.text.toString())
        }

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

    private fun setupRecyclerView() {
        fragmentRssFeedsBinding.apply {
            feedsRecyclerView.layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            rssFeedAdapter.setHasStableIds(true)
            feedsRecyclerView.adapter = rssFeedAdapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRSSFeedClickedListener) {
            onRSSFeedClickedListener = context
        }
    }
}