package id.naupal.network

import com.squareup.moshi.Moshi
import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection
import retrofit2.Response

sealed class ResourceState<out T> {
    class Loading<out T> : ResourceState<T>()
    data class Success<out T>(val data: T) : ResourceState<T>()
    data class Failure<out T>(
        val throwable: Throwable? = null,
        val responseCode: Int? = null,
        val message: String? = null,
        val errorJsonObj: JSONObject? = null
    ) : ResourceState<T>() {
        inline fun <reified E : Any> getErrorBody(): E? {
            return try {
                val error = errorJsonObj?.getString("error")
                error?.fromJson()
            } catch (e: JSONException) {
                null
            }
        }

        /**
         * Based on [safeApiCall] & [safeApiCallFlow]
         * If resource is not exist, response code will be 404, has message and throwable would be null
         * This might happen when BE hasn't handled empty / nonexistent data
         */
        inline fun isResourceNotFound(): Boolean {
            return responseCode ?: -1 == HttpURLConnection.HTTP_NOT_FOUND && throwable == null && message != null
        }

        /**
         * Check error response body with respective BE team
         * in case they still not use this format
         */
        fun getErrorCode(): String? {
            return errorJsonObj
                ?.optJSONObject("error")
                ?.optString("code").orEmpty().ifEmpty { responseCode?.toString() }
        }

        fun getErrorMessage(): String? {
            return errorJsonObj
                ?.getJSONObject("error")
                ?.getString("message")
        }
    }
}

suspend fun <T : Any, R : Any> safeApiCall(
    mapper: Mapper<T, R>,
    call: suspend () -> Response<T>
): ResourceState<R> {
    var responseCode: Int? = null
    var errorMessage: String? = null
    var objError: JSONObject? = null

    try {
        val response = call.invoke()
        if (response.isSuccessful) {
            response.body()?.let {
                return ResourceState.Success(mapper.map(it))
            } ?: run {
                return ResourceState.Success(Unit as R)
            }
        }

        responseCode = response.code()

        objError = JSONObject(response.errorBody()?.string())
        errorMessage = objError.getString("error")
        return ResourceState.Failure(
            null,
            responseCode = responseCode,
            message = errorMessage,
            errorJsonObj = objError
        )

    } catch (e: Exception) {
        return ResourceState.Failure(
            throwable = e,
            responseCode = responseCode,
            message = errorMessage,
            errorJsonObj = objError
        )
    }
}

interface Mapper<I, O> {
    fun map(input: I): O
}

inline fun <reified T : Any> String.fromJson(customBuilder: Moshi.Builder = Moshi.Builder()): T? =
    customBuilder
        .build()
        .adapter(T::class.java)
        .fromJson(this)