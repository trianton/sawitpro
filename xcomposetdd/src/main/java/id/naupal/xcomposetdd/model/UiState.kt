package id.naupal.xcomposetdd.model

data class UiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val contacts: List<Contact> = emptyList()
);