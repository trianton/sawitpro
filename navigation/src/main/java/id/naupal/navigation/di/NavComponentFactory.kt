package id.naupal.navigation.di

import android.content.Context

/**
 * Created by Naupal T. on 06/05/22.
 */

object NavComponentFactory {
    private lateinit var navComponent: NavComponent
    fun create(context: Context): NavComponent {
        if (!NavComponentFactory::navComponent.isInitialized) {
            navComponent = DaggerNavComponent.factory().create(
                NavModule(context)
            )
        }
        return navComponent
    }
}