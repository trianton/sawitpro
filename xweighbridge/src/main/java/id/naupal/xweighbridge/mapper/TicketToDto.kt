package id.naupal.xweighbridge.mapper

import id.naupal.firebase.model.TicketDto
import id.naupal.utils.Mapper
import id.naupal.xweighbridge.model.Ticket
import javax.inject.Inject

class TicketToDto @Inject constructor() : Mapper<Ticket, TicketDto> {
    override fun map(input: Ticket): TicketDto {
        return TicketDto(
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