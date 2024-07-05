package com.bolicstudio.xrocketnews.model

import id.naupal.news.api.domain.model.Article

sealed class UiState {

    data class Loading(val msg: String? = null) : UiState()

    data class Error(
        val throwable: Throwable? = null,
        val responseCode: Int? = null,
        val error: String? = null
    ) : UiState()

    sealed class Success<out T>(val data: T) : UiState() {
        data class GetNews(val news: List<Article>) : Success<List<Article>>(news)
    }
}