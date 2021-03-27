package com.newyork.data.local.database.Article

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Insert
    suspend fun insert(item: List<ArticleEntity>)

    @Query("delete from article")
    suspend fun clear()

    @Query("select * from article")
    fun getNews(): Flow<List<ArticleEntity>>
}