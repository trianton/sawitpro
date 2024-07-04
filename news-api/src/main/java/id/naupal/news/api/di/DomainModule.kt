package id.naupal.news.api.di

import dagger.Module
import dagger.Provides
import id.naupal.news.api.data.repository.NewsRepository
import id.naupal.news.api.domain.usecase.GetArticlesUseCase
import id.naupal.news.api.domain.usecase.GetArticlesUseCaseImpl
import id.naupal.news.api.domain.usecase.GetNewsUseCase
import id.naupal.news.api.domain.usecase.GetNewsUseCaseImpl

@Module
abstract class DomainModule {

    @Module
    companion object {

        @NewsApiScope
        @Provides
        fun provideGetNewsUseCase(
            repository: NewsRepository
        ): GetNewsUseCase = GetNewsUseCaseImpl(repository)

        @NewsApiScope
        @Provides
        fun provideGetArticlesUseCase(
            repository: NewsRepository
        ): GetArticlesUseCase = GetArticlesUseCaseImpl(repository)
    }
}