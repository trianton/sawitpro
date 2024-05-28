package id.naupal.network.di

import android.content.Context
import dagger.Module
import dagger.Provides
import id.naupal.network.DaggerNamed.PLAIN_OKHTTP
import id.naupal.network.HttpClientBuilderFactory
import okhttp3.OkHttpClient
import javax.inject.Named

@Module
class NetworkModule(
    private val context: Context
) {

    @Provides
    @NetworkScope
    @Named(PLAIN_OKHTTP)
    fun provideOkHttp(
    ): OkHttpClient {
        return HttpClientBuilderFactory(context).create().build()
    }

}