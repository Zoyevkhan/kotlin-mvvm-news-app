package com.news.app.ui.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.news.app.R
import com.news.app.databinding.FragmentNewsFragmentBinding
import com.news.app.ui.adapters.NewsAdapter
import com.news.app.ui.home.HomeActivity
import com.news.app.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


abstract class BaseFragment(layout: Int) : Fragment(layout) {
    val mHomeViewModel: HomeViewModel by activityViewModels()
    lateinit var title: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle()
    }
    abstract fun setTitle()




}


