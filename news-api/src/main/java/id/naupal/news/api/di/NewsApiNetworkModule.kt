package id.naupal.news.api.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import id.naupal.network.DaggerNamed
import id.naupal.news.api.data.service.NewsApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
object NewsApiNetworkModule {

    @NewsApiScope
    @Provides
    fun provideRetrofitClient(
        @Named(DaggerNamed.PLAIN_OKHTTP) okHttpClient: OkHttpClient
    ): Retrofit {


        val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi)) //for parsing KotlinObjects i.e.
            .baseUrl("https://saurav.tech/NewsAPI/everything/")
            .client(okHttpClient)
            .build()
    }

    @NewsApiScope
    @Provides
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

}