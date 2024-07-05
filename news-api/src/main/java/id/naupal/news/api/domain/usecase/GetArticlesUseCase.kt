package id.naupal.news.api.domain.usecase

import id.naupal.news.api.domain.model.Article


interface GetArticlesUseCase {
    suspend operator fun invoke(): List<Article>
}