package id.naupal.news.api.domain.usecase

import id.naupal.network.ResourceState
import id.naupal.news.api.domain.model.Article


interface GetArticlesUseCase {
    suspend operator fun invoke(): ResourceState<List<Article>>
}