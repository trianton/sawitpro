package id.naupal.news.api.di

import dagger.Binds
import dagger.Module
import id.naupal.network.Mapper
import id.naupal.news.api.data.dto.NewsDto
import id.naupal.news.api.data.mapper.NewsApiResultMapper
import id.naupal.news.api.data.repository.NewsRepository
import id.naupal.news.api.data.repository.NewsRepositoryImpl
import id.naupal.news.api.domain.model.News

@Module
abstract class DataModule {

    @NewsApiScope
    @Binds
    abstract fun bindsNewsApiResultMapper(impl: NewsApiResultMapper): Mapper<List<NewsDto>, List<News>>

    @NewsApiScope
    @Binds
    abstract fun bindsNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}