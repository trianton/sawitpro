package id.naupal.network

import android.content.Context
import android.os.Build
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

class HttpClientBuilderFactory(private val context: Context) {

    companion object {
        private const val HEADER_DEVICE_BRAND = "X-Device-Brand"
        private const val HEADER_DEVICE_MODEL = "X-Device-Model"
        private const val HEADER_OS_VERSION = "X-Os-Version-Release"
        private const val HEADER_OS_VERSION_SDK_INT = "X-Os-Version-Sdk-Int"
    }

    private val cacheSize = (5 * 1024 * 1024).toLong()
    private val myCache = Cache(context.cacheDir, cacheSize)

    private val timeoutDurationInSeconds = 4L

    fun create(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
            .connectTimeout(timeoutDurationInSeconds, TimeUnit.SECONDS)
            .readTimeout(timeoutDurationInSeconds, TimeUnit.SECONDS)
            .writeTimeout(timeoutDurationInSeconds, TimeUnit.SECONDS)
            .cache(myCache)


        builder.addInterceptor {
            var request: Request = it.request()
            request = request.newBuilder()
                .header(HEADER_DEVICE_BRAND, Build.BRAND)
                .header(HEADER_DEVICE_MODEL, Build.MODEL)
                .header(HEADER_OS_VERSION, Build.VERSION.RELEASE)
                .header(HEADER_OS_VERSION_SDK_INT, Build.VERSION.SDK_INT.toString())
                .build()
            it.proceed(request)
        }

        return builder
    }
}