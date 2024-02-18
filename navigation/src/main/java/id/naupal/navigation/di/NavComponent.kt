package id.naupal.navigation.di

import dagger.Component
import id.naupal.navigation.Navigation

/**
 * Created by Naupal T. on 06/05/22.
 */

@Component(
    modules = [
        NavModule::class
    ],
    dependencies = [

    ]
)
interface NavComponent {

    @Component.Factory
    interface Factory {
        fun create(
            nvaModule: NavModule
        ): NavComponent
    }

    fun provide(): Navigation
}