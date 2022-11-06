package com.example.rssfeedapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedapp.adapters.RSSFeedAdapter
import com.example.rssfeedapp.databinding.FragmentRssFeedsBinding
import com.example.rssfeedapp.listeners.OnRSSFeedClickedListener
import com.example.rssfeedapp.model.Image
import com.example.rssfeedapp.model.RSS
import com.example.rssfeedapp.model.RSSFeed
import com.example.rssfeedapp.networking.RSSFeedApi
import com.example.rssfeedapp.viewmodel.RSSFeedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            requestCall(fragmentRssFeedsBinding.addLinkButton)
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRSSFeedClickedListener) {
            onRSSFeedClickedListener = context
        }
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

    private fun requestCall(view: View) {
        val api = RSSFeedApi.create()
        val rssFeedLink =
            rssFeedViewModel.changeLinkProtocol(
                fragmentRssFeedsBinding.addLinkTextInputEditText.text.toString()
            )
        val call = api.getRSS(rssFeedLink)

        fragmentRssFeedsBinding.progressBar.visibility = View.VISIBLE

        call.enqueue(object : Callback<RSS> {
            override fun onResponse(call: Call<RSS>, response: Response<RSS>) {
                response.body()?.channel?.let {
                    addRSSFeed(
                        it.title,
                        it.description,
                        it.image,
                        rssFeedLink
                    )
                }
                fragmentRssFeedsBinding.addLinkTextInputEditText.text?.clear()
                fragmentRssFeedsBinding.progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<RSS>, t: Throwable) {
                Log.e("TAG", "Failure" + t.message)
                fragmentRssFeedsBinding.addLinkTextInputEditText.text?.clear()
                fragmentRssFeedsBinding.progressBar.visibility = View.GONE
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addRSSFeed(title: String, description: String, image: Image, rssFeedURL: String) {
        val rssFeed = RSSFeed(0, title, description, image.url, rssFeedURL)
        rssFeedViewModel.insertRSSFeed(rssFeed)
    }
}