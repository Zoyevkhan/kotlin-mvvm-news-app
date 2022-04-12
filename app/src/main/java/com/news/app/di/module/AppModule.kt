package com.news.app.di.module

import android.content.Context
import com.news.app.database.sessionManager.PreferenceManager
import com.news.app.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideBaseUrl() = Constants.baseURL






}