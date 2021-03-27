package com.newyork.data.di

import android.content.Context
import androidx.room.Room
import com.newyork.data.local.database.AppDataBase
import com.newyork.data.local.database.Article.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class LocalCacheModule {

    @Singleton
    @Provides
    fun getRoomDatabase(@ApplicationContext context: Context): AppDataBase {

        return Room.databaseBuilder(context, AppDataBase::class.java, "news.db")
                .build()

    }

    @Singleton
    @Provides
    fun articleDao(database: AppDataBase): ArticleDao = database.articleDao()


}