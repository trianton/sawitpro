package id.naupal.news.api.domain.model

data class News(
    val id: String,
    val name: String,
    val costPerLaunch: Int,
    val height: Double,
    val weight: Int,
    val wikiUrl: String,
    val imageUrl: String,
)