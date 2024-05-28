package id.naupal.news.api.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsDto(
    @Json(name = "id")
    val id: String = "",

    @Json(name = "name")
    val name: String = "",

    @Json(name = "cost_per_launch")
    val costPerLaunch: Int = 0,

    @Json(name = "first_flight")
    val firstFlightDate: String = "",

    @Json(name = "height")
    val height: Height = Height(),

    @Json(name = "mass")
    val weight: Weight = Weight(),

    @Json(name = "wikipedia")
    val wikiUrl: String = "",

    @Json(name = "flickr_images")
    val imageUrls: List<String> = emptyList(),
) {
    @JsonClass(generateAdapter = true)
    data class Height(
        val meters: Double = 0.0,
        val feet: Double = 0.0,
    )

    @JsonClass(generateAdapter = true)
    data class Weight(
        val kg: Int = 0,
        val lb: Int = 0,
    )
}
