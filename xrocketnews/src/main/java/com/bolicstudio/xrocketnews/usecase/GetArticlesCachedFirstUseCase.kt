package com.bolicstudio.xrocketnews.usecase

import id.naupal.news.api.domain.model.Article


interface GetArticlesCachedFirstUseCase {
    suspend operator fun invoke(): List<Article>
}