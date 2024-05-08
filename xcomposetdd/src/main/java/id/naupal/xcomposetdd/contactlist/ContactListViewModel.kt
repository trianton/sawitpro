package id.naupal.xcomposetdd.contactlist

import androidx.lifecycle.ViewModel
import id.naupal.xcomposetdd.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ContactListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchContacts()
    }

    private fun fetchContacts() = viewModelScope.launch {
        //todo
    }

}