package com.bolicstudio.xrocketnews.presentation.newslist

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bolicstudio.xrocketnews.databinding.XrocketnewsActivityListNewsBinding
import com.bolicstudio.xrocketnews.di.RocketNewsComponentFactory
import com.bolicstudio.xrocketnews.model.UiState
import id.naupal.ui.BaseViewBindingActivity
import id.naupal.utils.extension.observe
import javax.inject.Inject

class NewsListActivity : BaseViewBindingActivity<XrocketnewsActivityListNewsBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var adapter: NewsAdapter

    override fun getLayoutBinding() =
        XrocketnewsActivityListNewsBinding.inflate(layoutInflater)

    override fun setupDependencyInjection() {
        RocketNewsComponentFactory.createComponent(this).inject(this)
        newsListViewModel = ViewModelProvider(this, viewModelFactory)[NewsListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter {
            Toast.makeText(this, "${it.content} is clicked", Toast.LENGTH_SHORT).show()
        }
        binding.rvNews.adapter = adapter
        observe(newsListViewModel.getNewsState, ::handleGetNewsState)

        binding.fabAddTicket.setOnClickListener {
            newsListViewModel.getNewsData()
        }
    }

    private fun handleGetNewsState(uiState: UiState) {
        when (uiState) {
            is UiState.Success.GetNews -> {
                adapter.setData(uiState.news)
            }

            else -> {
                //todo
            }
        }

    }
}