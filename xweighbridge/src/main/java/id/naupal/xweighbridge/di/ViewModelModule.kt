package id.naupal.xweighbridge.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.naupal.utils.dagger.ViewModelFactory
import id.naupal.utils.dagger.ViewModelKey
import id.naupal.xweighbridge.presentation.listticket.ListOfTicketViewModel

/**
 * Created by Naupal T. on 08/05/22.
 */

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListOfTicketViewModel::class)
    internal abstract fun bindListOfTicketViewModel(viewModel: ListOfTicketViewModel): ViewModel
}
