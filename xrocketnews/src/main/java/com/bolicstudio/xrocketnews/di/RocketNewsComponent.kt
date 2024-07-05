package com.bolicstudio.xrocketnews.di

import com.bolicstudio.xrocketnews.presentation.newslist.NewsListActivity
import dagger.Component
import id.naupal.news.api.di.NewsApiComponent
import id.naupal.playground.di.AppComponent
import id.naupal.playground.di.FeatureScope

@FeatureScope
@Component(
    modules = [
        ViewModelModule::class,
    ],
    dependencies = [
        AppComponent::class,
        NewsApiComponent::class
    ]
)
interface RocketNewsComponent {
    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent,
            newsApiComponent: NewsApiComponent
        ): RocketNewsComponent
    }

    fun inject(activity: NewsListActivity)
}