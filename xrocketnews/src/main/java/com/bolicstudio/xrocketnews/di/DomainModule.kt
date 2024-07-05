package com.bolicstudio.xrocketnews.di

import com.bolicstudio.localstorage.repository.ArticleDbRepository
import com.bolicstudio.xrocketnews.usecase.GetArticlesCachedFirstUseCase
import com.bolicstudio.xrocketnews.usecase.GetArticlesCachedFirstUseCaseImpl
import dagger.Module
import dagger.Provides
import id.naupal.news.api.data.repository.NewsRepository

@Module
abstract class DomainModule {

    @Module
    companion object {

        @Provides
        fun provideGetNewsUseCase(
            newsRepository: NewsRepository,
            articleDbRepository: ArticleDbRepository
        ): GetArticlesCachedFirstUseCase = GetArticlesCachedFirstUseCaseImpl(newsRepository,articleDbRepository)
    }
}