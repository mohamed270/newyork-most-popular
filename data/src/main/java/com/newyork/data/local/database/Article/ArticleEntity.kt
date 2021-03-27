package com.newyork.data.local.database.Article

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity (
        var abstractDetails:String="",
        var author:String?="",
        var publishedDate:String?="",
        var title:String?="",

        )
{
    @PrimaryKey(autoGenerate = true)
    var localId: Int = 0
}

