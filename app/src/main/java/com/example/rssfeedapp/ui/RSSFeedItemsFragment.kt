package com.example.rssfeedapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssfeedapp.adapters.RSSFeedItemAdapter
import com.example.rssfeedapp.databinding.FragmentRssFeedItemsBinding
import com.example.rssfeedapp.listeners.OnRSSFeedItemClickedListener
import com.example.rssfeedapp.model.Channel
import com.example.rssfeedapp.model.RSS
import com.example.rssfeedapp.model.RSSFeed
import com.example.rssfeedapp.networking.RSSFeedApi
import com.example.rssfeedapp.viewmodel.RSSFeedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        arguments?.let {
            val rssFeed = it.getSerializable(KEY) as RSSFeed
            requestCall(rssFeed.rssFeedURL)
        }

        return rssFeedItemsFeedsBinding.root
    }

    private fun requestCall(url: String) {
        val api = RSSFeedApi.create()
        val call = api.getRSS(url)

        rssFeedItemsFeedsBinding.progressBarItems.visibility = View.VISIBLE

        call.enqueue(object : Callback<RSS> {
            override fun onResponse(call: Call<RSS>, response: Response<RSS>) {
                response.body()?.let { setupRecyclerView(it.channel) }
                rssFeedItemsFeedsBinding.progressBarItems.visibility = View.GONE
            }

            override fun onFailure(call: Call<RSS>, t: Throwable) {
                Log.e("TAG", "Failure" + t.message)
                rssFeedItemsFeedsBinding.progressBarItems.visibility = View.GONE
            }
        })
    }

    private fun setupRecyclerView(channel: Channel) {
        rssFeedItemsFeedsBinding.itemsRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        rssFeedItemsFeedsBinding.itemsRecyclerView.adapter =
            RSSFeedItemAdapter(
                rssFeedViewModel.loadRSSFeedItems(channel),
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