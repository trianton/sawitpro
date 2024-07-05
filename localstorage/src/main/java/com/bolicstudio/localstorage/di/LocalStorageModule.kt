package com.bolicstudio.localstorage.di

import android.content.Context
import com.bolicstudio.localstorage.dao.ArticleDao
import com.bolicstudio.localstorage.db.PlaygroundDb
import com.bolicstudio.localstorage.repository.ArticleDbRepository
import dagger.Module
import dagger.Provides

@Module
class LocalStorageModule(
    private val context: Context
) {

    @Provides
    fun providePosDb(): PlaygroundDb {
        return PlaygroundDb.buildDatabase(context)
    }

    @Provides
    fun provideProductDao(db: PlaygroundDb): ArticleDao {
        return db.articleDao()
    }

    @Provides
    fun provideArticleDbRepository(dao: ArticleDao): ArticleDbRepository {
        return ArticleDbRepository(dao)
    }
}