package com.bolicstudio.localstorage.di

import com.bolicstudio.localstorage.repository.ArticleDbRepository
import dagger.Component

@Component(
    modules = [
        LocalStorageModule::class
    ]
)
interface LocalStorageComponent {

    @Component.Factory
    interface Factory {
        fun create(
            localStorageModule: LocalStorageModule
        ): LocalStorageComponent
    }

    fun provideProductRepo(): ArticleDbRepository

}