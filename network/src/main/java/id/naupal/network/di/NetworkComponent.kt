package id.naupal.network.di

import dagger.Component
import id.naupal.network.DaggerNamed.PLAIN_OKHTTP
import okhttp3.OkHttpClient
import javax.inject.Named

@NetworkScope
@Component(
    modules = [NetworkModule::class],
    dependencies = []
)
interface NetworkComponent {

    @Component.Factory
    interface Factory {
        fun create(
            networkModule: NetworkModule
        ): NetworkComponent
    }

    @Named(PLAIN_OKHTTP)
    fun providePlainOkHttpClient(): OkHttpClient
}