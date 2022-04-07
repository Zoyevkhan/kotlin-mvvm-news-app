package com.news.app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.news.app.R
import com.news.app.databinding.FragmentNewsFragmentBinding
import com.news.app.pojo.NewsResponse
import com.news.app.ui.adapters.NewsAdapter
import com.news.app.ui.base.BaseFragment
import com.news.app.ui.home.HomeActivity
import com.news.app.ui.home.HomeViewModel
import com.news.app.ui.listners.NewsAdapterListener
import com.news.app.utils.Resource
import com.news.app.utils.extensions.showSnackBar
import kotlin.math.log


class NewsFragment() : BaseFragment(R.layout.fragment_news_fragment) {

    var _binding: FragmentNewsFragmentBinding?=null
   lateinit var mHomeActivityListener:NewsAdapterListener
     val binding get() = _binding!!
    lateinit var newsAdapter: NewsAdapter




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewsFragmentBinding.bind(view)
        (activity as HomeActivity).setTitle(title)
        (activity as HomeActivity).showCountryPicker(true)
        mHomeActivityListener=activity as NewsAdapterListener
        registerObserver()
        setRecycler()
    }
     fun registerObserver() {
        mHomeViewModel.getNews().observe(viewLifecycleOwner, Observer { Resource ->
            when (Resource) {
                is Resource.isLoading -> showProgress(true)
                is Resource.Success -> hanldeSuccessRespose(Resource.data)
                is Resource.Eror -> {
                    showProgress(false)
                    binding.root.showSnackBar(Resource.message)
                }
            }
        })
    }
    fun hanldeSuccessRespose(news: NewsResponse?) {
        showProgress(false)
        news?.let { news ->
            news.articles?.let { articles ->
                if (articles.size > 0) {
                    newsAdapter.updateArticles(news.articles)
                } else {
                    binding.root.showSnackBar(resources.getString(R.string.Data_NOT_FOUND))
                }
            }
        } ?: run {
            binding.root.showSnackBar(resources.getString(R.string.Data_NOT_FOUND))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
    fun setRecycler() {
        newsAdapter = NewsAdapter(mHomeActivityListener, mutableListOf())
        var newsLayoutManager = LinearLayoutManager(activity)
        val dividerItemDecoration =
            DividerItemDecoration(binding.recycler.getContext(), newsLayoutManager.getOrientation())
        binding.recycler.apply {
            adapter = newsAdapter
            layoutManager = newsLayoutManager
            addItemDecoration(dividerItemDecoration)
        }
        binding.recycler.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    (activity as HomeActivity).setBottomMenuVisibility(false);
                } else {
                    (activity as HomeActivity).setBottomMenuVisibility(true);
                }

            }
        })
    }
    fun showProgress(shouldShow: Boolean) {
        if (shouldShow) {
            binding.shimmerLayout.visibility = VISIBLE
            binding.shimmerLayout.startShimmer()
        } else {
            binding.shimmerLayout.visibility = GONE
            binding.shimmerLayout.stopShimmer()
        }

    }
    override fun setTitle() {
        title="News"
    }


}