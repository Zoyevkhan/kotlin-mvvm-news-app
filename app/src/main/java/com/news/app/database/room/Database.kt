package com.news.app.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.news.app.pojo.Article


@Database(entities = [Article::class], version = 1)
abstract class Database :RoomDatabase(){
    abstract fun articleDao(): ArticleDao
}