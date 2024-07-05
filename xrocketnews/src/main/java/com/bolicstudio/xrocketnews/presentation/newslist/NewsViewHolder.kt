package com.bolicstudio.xrocketnews.presentation.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bolicstudio.xrocketnews.databinding.XrocketnewsItemNewsBinding
import id.naupal.news.api.domain.model.Article

class NewsViewHolder(@LayoutRes layout: Int, parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false)) {

    fun bind(
        item: Article,
        onItemClicked: (item: Article) -> Unit
    ) = with(XrocketnewsItemNewsBinding.bind(itemView)) {
        txtDateTime.text = item.publishedAt
        txtDriverName.text = item.source
        txtLicenseNumber.text = item.description
        itemView.setOnClickListener { onItemClicked.invoke(item) }
    }
}