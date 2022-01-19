package br.com.android.victorcs.mvvmarvel.data.model.error


data class ErrorModel(
    val message: String?,
    val code: Int?,
    @Transient val statusCode: ErrorStatus
)