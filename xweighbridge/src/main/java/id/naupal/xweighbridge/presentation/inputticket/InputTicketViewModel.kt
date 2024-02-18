package id.naupal.xweighbridge.presentation.inputticket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.naupal.xweighbridge.model.Ticket
import id.naupal.xweighbridge.model.UiState
import id.naupal.xweighbridge.usecase.InsertTicketUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class InputTicketViewModel @Inject constructor(
    private val insertTicketUseCase: InsertTicketUseCase
) : ViewModel() {

    private val _insertTicketState = MutableLiveData<UiState>()
    val insertTicketState: LiveData<UiState> = _insertTicketState

    fun insertTicket(ticket: Ticket) = viewModelScope.launch {
        if (insertTicketState.value is UiState.Loading) return@launch

        _insertTicketState.value = UiState.Loading()

        val response = insertTicketUseCase(ticket)
        _insertTicketState.value = response
    }
}