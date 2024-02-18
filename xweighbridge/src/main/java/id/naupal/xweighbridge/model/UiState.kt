package id.naupal.xweighbridge.model

sealed class UiState {

    data class Loading(val msg: String? = null) : UiState()

    data class Error(
        val throwable: Throwable? = null,
        val responseCode: Int? = null,
        val error: String? = null
    ) : UiState()

    sealed class Success<out T>(val data: T) : UiState() {
        data class InputTicket(val ticket: Ticket) : Success<Ticket>(ticket)

        data class GetTickets(val tickets: List<Ticket>) : Success<List<Ticket>>(tickets)
    }
}