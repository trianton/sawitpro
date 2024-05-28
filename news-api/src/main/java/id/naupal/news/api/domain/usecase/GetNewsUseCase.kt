package id.naupal.news.api.domain.usecase

import id.naupal.network.ResourceState
import id.naupal.news.api.domain.model.News


interface GetNewsUseCase {
    suspend operator fun invoke(): ResourceState<List<News>>
}