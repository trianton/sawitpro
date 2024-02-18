package id.naupal.sawitpro.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import id.naupal.sawitpro.SawitProApplication
import id.naupal.utils.dagger.ViewModelFactory

/**
 * Created by Naupal T. on 06/05/22.
 */

@Module
abstract class AppModule {

    @Binds
    abstract fun bindApplication(app: SawitProApplication): Application

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}