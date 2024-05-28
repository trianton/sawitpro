package id.naupal.news.api.data.repository

import id.naupal.network.ResourceState
import id.naupal.news.api.domain.model.News

interface NewsRepository {
    suspend fun getRockets(): ResourceState<List<News>>
}
