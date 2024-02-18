package id.naupal.navigation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import id.naupal.navigation.Navigation

/**
 * Created by Naupal T. on 06/05/22.
 */

@Module
class NavModule(private val context: Context) {

    @Provides
    fun providePosNavigation(): Navigation = Navigation(context)
}