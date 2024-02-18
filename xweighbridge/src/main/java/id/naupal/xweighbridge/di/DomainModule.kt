package id.naupal.xweighbridge.di

import dagger.Binds
import dagger.Module
import id.naupal.xweighbridge.usecase.GetTicketsUseCase
import id.naupal.xweighbridge.usecase.GetTicketsUseCaseImpl
import id.naupal.xweighbridge.usecase.InsertTicketUseCase
import id.naupal.xweighbridge.usecase.InsertTicketUseCaseImpl

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindsCheckIsUserAdminUseCase(usecase: InsertTicketUseCaseImpl): InsertTicketUseCase

    @Binds
    abstract fun bindsGetTicketsUseCase(usecase: GetTicketsUseCaseImpl): GetTicketsUseCase
}