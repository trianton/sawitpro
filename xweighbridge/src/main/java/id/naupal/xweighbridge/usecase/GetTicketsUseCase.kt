package id.naupal.xweighbridge.usecase

import id.naupal.firebase.model.TicketFiler
import id.naupal.xweighbridge.model.UiState

/**
 * Created by Naupal T. on 14/11/22.
 */

interface GetTicketsUseCase {
    suspend operator fun invoke(sortBy: String,  filer: TicketFiler?): UiState
}