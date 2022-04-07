package com.news.app.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.news.app.R
import com.news.app.databinding.FragmentNewsSavedBinding
import com.news.app.databinding.FragmentNewsSearchBinding
import com.news.app.ui.adapters.NewsAdapter
import com.news.app.ui.base.BaseFragment
import com.news.app.ui.home.HomeActivity
import com.news.app.ui.listners.NewsAdapterListener
import com.news.app.utils.Resource
import com.news.app.utils.extensions.showSnackBar
import com.google.android.material.snackbar.Snackbar

import androidx.annotation.NonNull

import androidx.recyclerview.widget.ItemTouchHelper




class NewsSavedFragment(val mHomeActivityListener: NewsAdapterListener):BaseFragment(R.layout.fragment_news_saved) {
    var _binding: FragmentNewsSavedBinding?=null
    val binding get() = _binding!!
    lateinit var newsAdapter: NewsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewsSavedBinding.bind(view)
        initViews()

    }
    private fun initViews() {
        (activity as HomeActivity).setTitle(title)
        (activity as HomeActivity).showCountryPicker(false)
        getAllSavedNews();
        registerObserver()
        setRecycler()
    }

    private fun getAllSavedNews() {
       mHomeViewModel.getAllArtciles()
    }

    fun registerObserver() {
        mHomeViewModel.getSavedNews().observe(viewLifecycleOwner, Observer {
            newsAdapter.updateArticles(it)
        })
    }
    override fun setTitle() {
        title = "Saved News"
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
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
               mHomeViewModel.deleteArticle(newsAdapter.deleteItem(position))
            }

        }).attachToRecyclerView(binding.recycler)
    }
}