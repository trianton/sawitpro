package id.naupal.xweighbridge.usecase

import id.naupal.xweighbridge.model.Ticket
import id.naupal.xweighbridge.model.UiState

interface InsertTicketUseCase {
    suspend operator fun invoke(ticket: Ticket): UiState
}