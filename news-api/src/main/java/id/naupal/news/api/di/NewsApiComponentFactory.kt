package id.naupal.news.api.di

import android.content.Context
import id.naupal.network.di.NetworkComponentFactory

object NewsApiComponentFactory {
    private lateinit var component: NewsApiComponent

    fun create(context: Context): NewsApiComponent {
        if (!this::component.isInitialized) {
            component = DaggerNewsApiComponent.factory().create(
                networkComponent = NetworkComponentFactory.create(context)
            )
        }
        return component
    }
}