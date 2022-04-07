package com.news.app.di.module

import android.content.Context
import androidx.room.Room
import com.news.app.database.room.ArticleDao
import com.news.app.database.room.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): Database {
        return Room.databaseBuilder(
            appContext,
            Database::class.java,
            "zoyeb"
        ).build()
    }
    @Provides
    @Singleton
    fun provideArticleDao(database: Database):ArticleDao{
       return database.articleDao()
    }

}