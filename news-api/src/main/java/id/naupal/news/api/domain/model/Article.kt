package id.naupal.news.api.domain.model

data class Article(
    val source: String,
    val author: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)