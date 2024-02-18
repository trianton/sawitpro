package id.naupal.firebase.model

data class TicketDto(
    val id: String,
    val dateTime: Long,
    val licenceNumber: String,
    val driverName: String,
    val inboundWeight: Long,
    val outboundWeight: Long,
    val netWeight: Long
)