package com.news.app.ui.home

import androidx.lifecycle.LiveData
import com.news.app.database.room.ArticleDao
import com.news.app.network.ApiService
import com.news.app.pojo.Article
import com.news.app.pojo.NewsResponse
import com.news.app.utils.Constants
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap


class HomeRepo @Inject constructor(
    val apiService: ApiService,
    var articleDao: ArticleDao
) {
    suspend fun getNewsByCountry(country: String, category: String): Response<NewsResponse> {
        var params: HashMap<String, Any> = HashMap()
        params.put("country", country)
        params.put("category", category)
        params.put("apiKey", Constants.API_KEY)
        params.put("pageSize", Constants.PAGE_SIZE)
        return apiService.getNewsByCountry(params)
    }

    suspend fun getNewsBySearch(query: String): Response<NewsResponse> {
        return apiService.getNewsBySeach(query)
    }

    suspend fun addArticleToDatabase(article: Article) {
        articleDao.addArticle(article)
    }

    suspend fun getAllArticle(): List<Article> {
        return articleDao.getAllArticles()
    }

    suspend fun deleteArticle(deleteItem: Article) {
        articleDao.deleteArticle(deleteItem)
    }


}