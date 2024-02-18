package id.naupal.firebase.repo

import id.naupal.firebase.model.ResultFbState
import id.naupal.firebase.model.TicketDto
import id.naupal.firebase.model.TicketFiler

interface FirestoreRepo {
    suspend fun insertTicket(stok: TicketDto): ResultFbState

    suspend fun getTickets(sortBy: String, filter: TicketFiler?): ResultFbState
}