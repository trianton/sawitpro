package id.naupal.news.api.data.mapper

import id.naupal.network.Mapper
import id.naupal.news.api.data.dto.NewsDto
import id.naupal.news.api.domain.model.News
import javax.inject.Inject


class NewsApiResultMapper @Inject constructor() : Mapper<List<@JvmSuppressWildcards NewsDto>, List<@JvmSuppressWildcards News>> {
    override fun map(input: List<NewsDto>): List<News> {
        return input.map {
            News(
                id = it.id,
                name = it.name,
                costPerLaunch = it.costPerLaunch,
                height = it.height.meters,
                weight = it.weight.kg,
                wikiUrl = it.wikiUrl,
                imageUrl = it.imageUrls[0],
            )
        }
    }
}