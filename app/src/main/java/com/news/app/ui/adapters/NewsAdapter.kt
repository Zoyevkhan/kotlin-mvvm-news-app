package com.news.app.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.news.app.R
import com.news.app.databinding.ArticleItemBinding
import com.news.app.pojo.Article
import com.news.app.ui.home.HomeActivity
import com.news.app.ui.listners.NewsAdapterListener
import com.news.app.utils.extensions.loadWithGlide
import com.news.app.utils.extensions.setVisible


class NewsAdapter(val mListener: NewsAdapterListener, var articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.article_item, parent, false))
    override fun onBindViewHolder(holder: NewsAdapter.ArticleViewHolder, position: Int) {
        with(holder.binding) {
            if(!articles[position].isSaved){
                save.setVisible(true)
            }
            article=articles[position]
            music.setOnClickListener {
                /*mListener.mHomeViewModel.setVoiceText(articles[position].description)*/
            }
            root.setOnClickListener {
               mListener.onNewsItemClick(articles[position])
            }
            save.setOnClickListener {
                articles[position].isSaved=true
                save.setVisible(false)
                mListener.onClickItemToSave(articles[position])
            }

        }

    }

    override fun getItemCount() = articles.size
    class ArticleViewHolder(val binding: ArticleItemBinding) : RecyclerView.ViewHolder(binding.root)

    public fun updateArticles(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()

    }

    fun deleteItem(position: Int):Article {
       var temp= (articles as MutableList<Article>)
        var article=temp[position]
        temp.removeAt(position)
        updateArticles(temp)
        return article

    }
}