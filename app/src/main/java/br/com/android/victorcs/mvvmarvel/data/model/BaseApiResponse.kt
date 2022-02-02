package br.com.android.victorcs.mvvmarvel.data.model

data class BaseApiResponse<T>(
    val attributionHTML: String?,
    val attributionText: String?,
    val code: Int?,
    val copyright: String?,
    val data: Data<T>?,
    val etag: String?,
    val status: String?
)

data class Data<T>(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: List<T>?,
    val total: Int?
)