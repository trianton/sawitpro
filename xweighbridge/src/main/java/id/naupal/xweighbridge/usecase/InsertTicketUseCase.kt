package id.naupal.xweighbridge.usecase

import id.naupal.xweighbridge.model.Ticket
import id.naupal.xweighbridge.model.UiState

/**
 * Created by Naupal T. on 14/11/22.
 */

interface InsertTicketUseCase {
    suspend operator fun invoke(ticket: Ticket): UiState
}