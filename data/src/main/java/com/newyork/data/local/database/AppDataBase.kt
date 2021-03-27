package com.newyork.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.newyork.data.Util.Utils
import com.newyork.data.local.database.Article.ArticleDao
import com.newyork.data.local.database.Article.ArticleEntity


@Database(
entities =[ArticleEntity::class],
version =Utils.DB_VERSION
)
abstract class AppDataBase: RoomDatabase() {


    abstract fun articleDao(): ArticleDao

}