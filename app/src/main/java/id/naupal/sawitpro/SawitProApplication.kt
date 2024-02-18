package id.naupal.sawitpro

import android.app.Application
import id.naupal.navigation.di.NavComponentFactory
import id.naupal.sawitpro.di.AppComponent
import id.naupal.sawitpro.di.DaggerAppComponent

class SawitProApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = provideMainComponent()
    }

    private fun provideMainComponent(): AppComponent {
        return DaggerAppComponent
            .builder()
            .provideNavComponent(NavComponentFactory.create(this))
            .build()
    }
}