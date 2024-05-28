package id.naupal.news.api.domain.usecase

import id.naupal.network.ResourceState
import id.naupal.news.api.data.repository.NewsRepository
import id.naupal.news.api.domain.model.News
import javax.inject.Inject

class GetNewsUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : GetNewsUseCase {
    override suspend fun invoke(): ResourceState<List<News>> {
        return repository.getRockets()
    }
}