package id.naupal.news.api.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleDto(
    @Json(name = "status")
    val status: String? = "",

    @Json(name = "totalResults")
    val totalResults: Int? = 0,

    @Json(name = "articles")
    val articles: List<Article2>? = emptyList(),
) {
    @JsonClass(generateAdapter = true)
    data class Article2(
        @Json(name = "source")
        val source: Source2? = null,

        @Json(name = "author")
        val author: String? = "",

        @Json(name = "title")
        val title: String? = "",

        @Json(name = "description")
        val description: String? = "",

        @Json(name = "url")
        val url: String? = "",

        @Json(name = "urlToImage")
        val urlToImage: String? = "",

        @Json(name = "publishedAt")
        val publishedAt: String? = "",

        @Json(name = "content")
        val content: String? = "",
    )
    @JsonClass(generateAdapter = true)
    data class Source2(
        val id: String? = "",
        val name: String? = "",
    )
}