package id.naupal.xweighbridge.usecase

import id.naupal.firebase.model.TicketFiler
import id.naupal.xweighbridge.model.UiState

interface GetTicketsUseCase {
    suspend operator fun invoke(sortBy: String,  filer: TicketFiler?): UiState
}