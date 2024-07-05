package com.bolicstudio.localstorage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bolicstudio.localstorage.constan.Entity.articleEntity
import com.bolicstudio.localstorage.constan.Field

@Entity(tableName = articleEntity)
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = Field.source)
    val source: String,
    @ColumnInfo(name = Field.author)
    val author: String,
    @ColumnInfo(name = Field.description)
    val description: String,
    @ColumnInfo(name = Field.urlToImage)
    val urlToImage: String,
    @ColumnInfo(name = Field.publishedAt)
    val publishedAt: String,
    @ColumnInfo(name = Field.content)
    val content: String
)