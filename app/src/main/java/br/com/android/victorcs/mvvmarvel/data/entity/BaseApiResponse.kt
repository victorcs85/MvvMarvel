package br.com.android.victorcs.mvvmarvel.data.entity

import com.google.gson.annotations.SerializedName

data class BaseApiResponse<T>(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    @SerializedName("data")
    val dataResponse: Data<T>,
    val etag: String,
    val status: String
)

data class Data<T>(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<T>,
    val total: Int
)