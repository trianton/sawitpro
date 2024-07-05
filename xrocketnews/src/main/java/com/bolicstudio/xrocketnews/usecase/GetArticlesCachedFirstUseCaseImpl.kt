package com.bolicstudio.xrocketnews.usecase

import com.bolicstudio.localstorage.entity.ArticleEntity
import com.bolicstudio.localstorage.repository.ArticleDbRepository
import id.naupal.network.ResourceState
import id.naupal.news.api.data.repository.NewsRepository
import id.naupal.news.api.domain.model.Article
import javax.inject.Inject

class GetArticlesCachedFirstUseCaseImpl @Inject constructor(
    private val repository: NewsRepository,
    private val articleDbRepository: ArticleDbRepository
) : GetArticlesCachedFirstUseCase {
    override suspend fun invoke(): List<Article> {
        when (val result = repository.getArticles()) {
            is ResourceState.Success -> {
                articleDbRepository.deleteAll()
                articleDbRepository.insertBelanja(result.data.map {
                    ArticleEntity(
                        source = it.source,
                        author = it.author,
                        urlToImage = it.urlToImage,
                        description = it.description,
                        publishedAt = it.publishedAt,
                        content = it.content
                    )
                })
            }
            else -> {
                //todo handle for another case
            }
        }
        return articleDbRepository.getAllArticle().toArticle()
    }

    private fun List<ArticleEntity>.toArticle(): List<Article> {
        return this.map {
            Article(
                source = it.source,
                author = it.author,
                urlToImage = it.urlToImage,
                description = it.description,
                publishedAt = it.publishedAt,
                content = it.content
            )
        }
    }
}