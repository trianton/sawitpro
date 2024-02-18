package id.naupal.xweighbridge.usecase

import id.naupal.firebase.model.ResultFbState
import id.naupal.firebase.model.TicketDto
import id.naupal.firebase.model.TicketFiler
import id.naupal.firebase.repo.FirestoreRepo
import id.naupal.utils.Mapper
import id.naupal.xweighbridge.model.Ticket
import id.naupal.xweighbridge.model.UiState
import javax.inject.Inject

/**
 * Created by Naupal T. on 14/11/22.
 */

class GetTicketsUseCaseImpl @Inject constructor(
    private val firestoreRepo: FirestoreRepo,
    private val mapDtoToTicket: Mapper<TicketDto, Ticket>
) : GetTicketsUseCase {

    override suspend fun invoke(sortBy: String, filter: TicketFiler?): UiState {
        return when (val res = firestoreRepo.getTickets(sortBy, filter)) {
            is ResultFbState.Loading -> {
                UiState.Loading(res.msg)
            }

            is ResultFbState.Error -> {
                UiState.Error(throwable = res.throwable, error = res.message)
            }

            is ResultFbState.Success.GetTickets -> {
                UiState.Success.GetTickets(
                    res.result.map { mapDtoToTicket.map(it) }
                )
            }

            else -> {
                UiState.Loading()
            }
        }
    }
}