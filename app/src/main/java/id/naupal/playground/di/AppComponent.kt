package id.naupal.playground.di

import com.bolicstudio.localstorage.di.LocalStorageComponent
import com.bolicstudio.localstorage.di.LocalStorageModule
import com.bolicstudio.localstorage.repository.ArticleDbRepository
import dagger.Component
import id.naupal.navigation.Navigation
import id.naupal.navigation.di.NavComponent
import id.naupal.playground.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ],
    dependencies = [
        LocalStorageComponent::class,
        NavComponent::class,
    ]
)

interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            localStorageComponent: LocalStorageComponent,
            navComponent: NavComponent
        ): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun provideNavigation(): Navigation
}