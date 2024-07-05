package com.bolicstudio.localstorage.di

import android.content.Context

object LocalStorageComponentFactory {
    private lateinit var localStorageComponent: LocalStorageComponent

    fun create(context: Context): LocalStorageComponent {
        if (!this::localStorageComponent.isInitialized) {
            localStorageComponent = DaggerLocalStorageComponent.factory().create(
            LocalStorageModule(context))

        }
        return localStorageComponent
    }
}