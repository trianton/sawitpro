package com.bolicstudio.xrocketnews.di

import android.content.Context
import com.bolicstudio.localstorage.di.LocalStorageComponentFactory
import id.naupal.news.api.di.NewsApiComponentFactory
import id.naupal.playground.PlaygorundApp


object RocketNewsComponentFactory {

    fun createComponent(context: Context): RocketNewsComponent {
        return DaggerRocketNewsComponent.factory().create(
            appComponent = PlaygorundApp.appComponent,
            newsApiComponent = NewsApiComponentFactory.create(context),
            localStorageComponent = LocalStorageComponentFactory.create(context)
        )
    }
}