package com.bolicstudio.xrocketnews.presentation.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bolicstudio.xrocketnews.model.UiState
import id.naupal.news.api.domain.usecase.GetArticlesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val getArticleUseCase: GetArticlesUseCase
) : ViewModel() {

    private val _getNewsState = MutableLiveData<UiState>()
    val getNewsState: LiveData<UiState> = _getNewsState

    fun getNewsData() = viewModelScope.launch(Dispatchers.IO) {
        _getNewsState.postValue(UiState.Success.GetNews(getArticleUseCase.invoke()))
    }

}