package id.naupal.news.api.data.repository

import id.naupal.network.Mapper
import id.naupal.network.ResourceState
import id.naupal.network.safeApiCall
import id.naupal.news.api.data.dto.ArticleDto
import id.naupal.news.api.data.dto.NewsDto
import id.naupal.news.api.data.service.NewsApiService
import id.naupal.news.api.domain.model.Article
import id.naupal.news.api.domain.model.News
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val service: NewsApiService,
    private val mapperNews: Mapper<List<NewsDto>, List<News>>,
    private val mapperArticle: Mapper<ArticleDto, List<Article>>,
) : NewsRepository {

    override suspend fun getRockets(): ResourceState<List<News>> {
        return safeApiCall(mapperNews) { service.getNews() }
    }

    override suspend fun getArticles(): ResourceState<List<Article>> {
        return safeApiCall(mapperArticle) { service.getArticles() }
    }
}
