package id.naupal.firebase.model

data class TicketFiler(
    var starDate: Long = 0,
    var endDate: Long = 0,
    var driverName: String = "",
    var licenseNumber: String = ""
)