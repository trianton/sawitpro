package id.naupal.playground.di

import dagger.Component
import id.naupal.navigation.Navigation
import id.naupal.navigation.di.NavComponent
import id.naupal.network.DaggerNamed
import id.naupal.network.di.NetworkComponent
import id.naupal.playground.MainActivity
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Naupal T. on 06/05/22.
 */

@Singleton
@Component(
    modules = [
        AppModule::class
    ],
    dependencies = [
        NavComponent::class,
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        fun provideNavComponent(navComponent: NavComponent): Builder
        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun provideNavigation(): Navigation
}