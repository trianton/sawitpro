package com.bolicstudio.localstorage.repository

import com.bolicstudio.localstorage.dao.ArticleDao
import com.bolicstudio.localstorage.entity.ArticleEntity

class ArticleDbRepository(private val dao: ArticleDao) {

    fun getAllArticle(): List<ArticleEntity> {
        return dao.getAll()
    }

    fun insertBelanja(entities: List<ArticleEntity>) {
        return dao.insertAll(entities)
    }

    fun deleteAll() {
        dao.deleteAll()
    }
}