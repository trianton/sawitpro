package id.naupal.playground

import android.app.Application
import com.bolicstudio.localstorage.di.LocalStorageComponentFactory
import id.naupal.navigation.di.NavComponentFactory
import id.naupal.playground.di.AppComponent
import id.naupal.playground.di.DaggerAppComponent


class PlaygorundApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = provideMainComponent()
    }

    private fun provideMainComponent(): AppComponent {
        return DaggerAppComponent.factory()
            .create(
                navComponent = NavComponentFactory.create(this),
                localStorageComponent = LocalStorageComponentFactory.create(this)
            )
    }
}