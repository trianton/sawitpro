package id.naupal.xweighbridge.presentation.listticket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.naupal.firebase.model.TicketFiler
import id.naupal.xweighbridge.model.UiState
import id.naupal.xweighbridge.usecase.GetTicketsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListOfTicketViewModel @Inject constructor(
    private val getTicketsUseCase: GetTicketsUseCase
) : ViewModel() {

    private val _getTicketsState = MutableLiveData<UiState>()
    val getTicketsState: LiveData<UiState> = _getTicketsState

    fun getTickets(sortBy: String, filter: TicketFiler?) = viewModelScope.launch {
        if (_getTicketsState.value is UiState.Loading) return@launch

        _getTicketsState.value = UiState.Loading()

        val response = getTicketsUseCase(sortBy, filter)
        _getTicketsState.value = response
    }
}