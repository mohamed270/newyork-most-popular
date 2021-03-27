package com.newyork.data.di


import com.newyork.data.repository.NewsRepository
import com.newyork.domain.repository.INewsRepository
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun getNewsRepository(repo: NewsRepository): INewsRepository




}
