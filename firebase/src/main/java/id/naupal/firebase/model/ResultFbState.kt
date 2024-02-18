package id.naupal.firebase.model

/**
 * Created by Naupal T. on 26/10/22.
 */

sealed class ResultFbState {

    data class Loading(val msg: String? = null) : ResultFbState()

    data class Error(
        val throwable: Throwable? = null,
        val message: String? = null,
    ) : ResultFbState()

    sealed class Success<out T>(val data: T) : ResultFbState() {
        data class InsertTicket(val result: TicketDto) : Success<TicketDto>(result)
        data class GetTickets(val result: List<TicketDto>) : Success<List<TicketDto>>(result)
    }
}