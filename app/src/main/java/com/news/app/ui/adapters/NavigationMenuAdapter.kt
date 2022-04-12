package com.news.app.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.news.app.R
import com.news.app.databinding.ActivityMainBinding
import com.news.app.databinding.NavRecyclerItemBinding
import com.news.app.ui.home.HomeActivity
import com.news.app.utils.Constants.getDummyList

class NavigationMenuAdapter(val context: Context) :
    RecyclerView.Adapter<NavigationMenuAdapter.MyViewHolder>() {
    var selectedPosition:Int=-1
    val list = mutableListOf<String>()
    init {
        list.getDummyList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        NavRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position == selectedPosition) {
            fillTheColor(holder, context.resources.getColor(R.color.green));
        } else {
            fillTheColor(holder, context.resources.getColor(R.color.black));
        }
        with(holder.binding) {
            catName.text = list[position]
            number.text=(position+1).toString()
            root.setOnClickListener {
                if(selectedPosition!=position){
                    fillTheColor(holder, context.resources.getColor(R.color.green));
                }
                selectedPosition=position
                notifyDataSetChanged()
                (context as HomeActivity).navMenuClicked(catName.text.toString())

            }
        }
    }

    override fun getItemCount() = list.size

    class MyViewHolder(val binding: NavRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)
    fun fillTheColor(holder:MyViewHolder,color:Int){
        with(holder.binding){
            catName.setTextColor(color)
            number.setTextColor(color)
        }
    }
}