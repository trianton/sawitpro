package id.naupal.news.api.domain.usecase

import android.util.Log
import id.naupal.network.ResourceState
import id.naupal.news.api.data.repository.NewsRepository
import id.naupal.news.api.domain.model.Article
import javax.inject.Inject

class GetArticlesUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : GetArticlesUseCase {
    override suspend fun invoke(): List<Article> {
        when (val result = repository.getArticles()) {
            is ResourceState.Success -> {
                return result.data
            }
            else -> {
                Log.e("NTR-GetArticlesUseCase","$result")
                return emptyList()

            }
        }
    }
}