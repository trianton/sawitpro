package id.naupal.network.di

import android.content.Context

object NetworkComponentFactory {
    private lateinit var networkComponent: NetworkComponent
    fun create(context: Context): NetworkComponent {
        if (!this::networkComponent.isInitialized) {
            networkComponent = DaggerNetworkComponent.factory().create(
                networkModule = NetworkModule(context)
            )
        }
        return networkComponent
    }
}