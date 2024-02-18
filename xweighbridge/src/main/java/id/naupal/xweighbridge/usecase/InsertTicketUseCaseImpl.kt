package id.naupal.xweighbridge.usecase

import id.naupal.firebase.model.ResultFbState
import id.naupal.firebase.model.TicketDto
import id.naupal.firebase.repo.FirestoreRepo
import id.naupal.utils.Mapper
import id.naupal.xweighbridge.model.Ticket
import id.naupal.xweighbridge.model.UiState
import javax.inject.Inject

/**
 * Created by Naupal T. on 14/11/22.
 */

class InsertTicketUseCaseImpl @Inject constructor(
    private val firestoreRepo: FirestoreRepo,
    private val mapTicketToDto: Mapper<Ticket, TicketDto>,
    private val mapDtoToTicket: Mapper<TicketDto, Ticket>
) : InsertTicketUseCase {

    override suspend fun invoke(ticket: Ticket): UiState {
        return when (val res = firestoreRepo.insertTicket(mapTicketToDto.map(ticket))) {
            is ResultFbState.Loading -> {
                UiState.Loading(res.msg)
            }

            is ResultFbState.Error -> {
                UiState.Error(throwable = res.throwable, error = res.message)
            }

            is ResultFbState.Success.InsertTicket -> {
                UiState.Success.InputTicket(mapDtoToTicket.map(res.result))
            }

            else -> {
                UiState.Loading()
            }
        }
    }
}