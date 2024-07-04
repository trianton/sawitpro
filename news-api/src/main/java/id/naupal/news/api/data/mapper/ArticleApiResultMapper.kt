package id.naupal.news.api.data.mapper

import id.naupal.network.Mapper
import id.naupal.news.api.data.dto.ArticleDto
import id.naupal.news.api.domain.model.Article
import javax.inject.Inject


class ArticleApiResultMapper @Inject constructor() : Mapper<@JvmSuppressWildcards ArticleDto, List<@JvmSuppressWildcards Article>> {
    override fun map(input: ArticleDto): List<Article> {
        return input.articles.map {
            Article(
                source = it.source?.name?:"-",
                author = it.author,
                description = it.description,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content
            )
        }
    }
}