package com.bolicstudio.localstorage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bolicstudio.localstorage.entity.ArticleEntity

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article_entity")
    fun getAll(): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<ArticleEntity>)

    @Query("DELETE FROM article_entity")
    fun deleteAll()
}