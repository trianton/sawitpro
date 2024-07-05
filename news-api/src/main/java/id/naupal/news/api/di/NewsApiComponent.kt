package id.naupal.news.api.di

import dagger.Component
import id.naupal.network.di.NetworkComponent
import id.naupal.news.api.domain.usecase.GetArticlesUseCase
import id.naupal.news.api.domain.usecase.GetNewsUseCase

@NewsApiScope
@Component(
    modules = [
        NewsApiNetworkModule::class,
        DataModule::class,
        DomainModule::class
    ],
    dependencies = [
        NetworkComponent::class,
    ]
)

interface NewsApiComponent {

    @Component.Factory
    interface Factory {
        fun create(
            networkComponent: NetworkComponent,
        ): NewsApiComponent
    }

    fun provideGetNewsUseCase(): GetNewsUseCase

    fun provideGetArticlesUseCase(): GetArticlesUseCase

}