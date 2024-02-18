package id.naupal.xweighbridge.di

import dagger.Binds
import dagger.Module
import id.naupal.firebase.model.TicketDto
import id.naupal.utils.Mapper
import id.naupal.xweighbridge.mapper.DtoToTicket
import id.naupal.xweighbridge.mapper.TicketToDto
import id.naupal.xweighbridge.model.Ticket

@Module
abstract class MapperModule {

    @Binds
    abstract fun bindDtoToTicket(impl: DtoToTicket): Mapper<TicketDto, Ticket>

    @Binds
    abstract fun bindTicketToDto(impl: TicketToDto): Mapper<Ticket, TicketDto>

}