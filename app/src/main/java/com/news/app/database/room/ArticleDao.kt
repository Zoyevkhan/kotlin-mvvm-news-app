package com.news.app.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.news.app.pojo.Article

@Dao
interface ArticleDao {
    @Query("Select * from article")
    suspend  fun getAllArticles():List<Article>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: Article)
    @Delete
    suspend fun deleteArticle(article: Article)
}