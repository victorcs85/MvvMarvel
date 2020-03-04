package br.com.android.victorcs.mvvmarvel.data.model

import com.google.gson.annotations.SerializedName

data class GenericItem(
    @SerializedName("resourceURI")
    val resourceUri: String?,
    val name: String?,
    val type: String?
)