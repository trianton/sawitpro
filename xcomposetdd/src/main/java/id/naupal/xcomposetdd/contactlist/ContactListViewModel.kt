package id.naupal.xcomposetdd.contactlist

import androidx.lifecycle.ViewModel
import id.naupal.xcomposetdd.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactListViewModel(val contactRepository: ContactRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(isLoading = true) }
        fetchContacts()
    }

    private fun fetchContacts() = viewModelScope.launch {
        runCatching {
            contactRepository.getContacts()
        }.onSuccess {
            _uiState.update { it.copy(isLoading = false) }
        }.onFailure {
            _uiState.update { it.copy(isLoading = false) }
        }
    }

}