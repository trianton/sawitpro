package id.naupal.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResult<T>(
    @Json(name = "results")
    val results: T? = null
)
