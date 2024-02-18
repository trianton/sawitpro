package id.naupal.xweighbridge.mapper

import id.naupal.firebase.model.TicketDto
import id.naupal.utils.Mapper
import id.naupal.xweighbridge.model.Ticket
import javax.inject.Inject

class DtoToTicket @Inject constructor() : Mapper<TicketDto, Ticket> {
    override fun map(input: TicketDto): Ticket {
        return Ticket(
            input.id,
            input.dateTime,
            input.licenceNumber,
            input.driverName,
            input.inboundWeight,
            input.outboundWeight,
            input.netWeight
        )
    }
}