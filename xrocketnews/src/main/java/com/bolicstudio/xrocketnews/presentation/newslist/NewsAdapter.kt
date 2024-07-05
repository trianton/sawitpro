package com.bolicstudio.xrocketnews.presentation.newslist

import android.os.Handler
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bolicstudio.xrocketnews.R
import id.naupal.news.api.domain.model.Article

class NewsAdapter(
    private val onClick: (item: Article) -> Unit,
) : RecyclerView.Adapter<NewsViewHolder>() {

    private var items: List<Article> = mutableListOf()

    fun setData(newItems: List<Article>) {
        items = newItems
        Handler().post { notifyDataSetChanged() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(R.layout.xrocketnews_item_news, parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }
}