package com.news.app.pojo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


data class NewsResponse(
    var status:String,
    var totalResults: Double,
    var articles: List<Article>
)
@Entity
public data class Article(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var author:String?,
    var title:String,
    var description:String,
    var url:String?,
    var urlToImage:String?,
    var publishedAt:String?,
    var content:String?,
){
    @Ignore
    var isSaved:Boolean=false
}
data class Source(
    var id:String,
    var name:String
)
