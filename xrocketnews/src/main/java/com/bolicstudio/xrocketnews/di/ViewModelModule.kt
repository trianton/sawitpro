package com.bolicstudio.xrocketnews.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bolicstudio.xrocketnews.presentation.newslist.NewsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.naupal.utils.dagger.ViewModelFactory
import id.naupal.utils.dagger.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    internal abstract fun bindNewsListViewModel(viewModel: NewsListViewModel): ViewModel
}
