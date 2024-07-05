package id.naupal.news.api.data.service

import id.naupal.news.api.data.dto.ArticleDto
import id.naupal.news.api.data.dto.NewsDto
import retrofit2.Response
import retrofit2.http.GET

interface NewsApiService {

    @GET("rockets")
    suspend fun getNews(): Response<List<NewsDto>>

    @GET("cnn.json")
    suspend fun getArticles(): Response<ArticleDto>

}