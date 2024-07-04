package id.naupal.news.api.domain.usecase

import id.naupal.network.ResourceState
import id.naupal.news.api.data.repository.NewsRepository
import id.naupal.news.api.domain.model.Article
import javax.inject.Inject

class GetArticlesUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : GetArticlesUseCase {
    override suspend fun invoke(): ResourceState<List<Article>> {
        return repository.getArticles()
    }
}