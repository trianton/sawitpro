package id.naupal.playground.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import id.naupal.playground.PlaygorundApp
import id.naupal.utils.dagger.ViewModelFactory

@Module
abstract class AppModule {

    @Binds
    abstract fun bindApplication(app: PlaygorundApp): Application

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}