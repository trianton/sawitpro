package id.naupal.news.api.di

import dagger.Module
import dagger.Provides
import id.naupal.network.DaggerNamed
import id.naupal.news.api.data.service.NewsApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named

@Module
object NewsApiNetworkModule {

    private const val SOCIAL_RETROFIT_CLIENT = "SOCIAL_RETROFIT_CLIENT"

    @NewsApiScope
    @Provides
    fun provideRetrofitClient(
        @Named(DaggerNamed.PLAIN_OKHTTP) okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("baseUrl")
            .client(okHttpClient)
            .build()
    }

    @NewsApiScope
    @Provides
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

}