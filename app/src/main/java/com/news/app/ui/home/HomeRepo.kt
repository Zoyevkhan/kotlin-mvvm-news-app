package com.news.app.ui.home

import androidx.lifecycle.LiveData
import com.news.app.database.room.ArticleDao
import com.news.app.network.ApiService
import com.news.app.pojo.Article
import com.news.app.pojo.NewsResponse
import retrofit2.Response
import javax.inject.Inject


class HomeRepo @Inject constructor(
    val apiService: ApiService,
    var articleDao: ArticleDao
){
    suspend fun getNewsByCountry(country:String): Response<NewsResponse> {
        return apiService.getNewsByCountry(country)
    }
    suspend fun getNewsBySearch(query:String): Response<NewsResponse> {
        return apiService.getNewsBySeach(query)
    }
    suspend fun addArticleToDatabase(article: Article){
        articleDao.addArticle(article)
    }
   suspend  fun getAllArticle():List<Article>{
        return articleDao.getAllArticles()
    }

    suspend fun deleteArticle(deleteItem: Article) {
            articleDao.deleteArticle(deleteItem)
    }


}