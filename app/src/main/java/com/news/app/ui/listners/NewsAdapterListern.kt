package com.news.app.ui.listners

import com.news.app.pojo.Article
import java.io.Serializable

interface NewsAdapterListener  {
    fun onNewsItemClick(article:Article)
    fun onClickItemToSave(article:Article)
}