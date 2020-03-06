package br.com.android.victorcs.mvvmarvel.data.model.error

import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @SerializedName("status")
    val message: String?,
    val code: Int?,
    @Transient val statusCode: ErrorStatus
)