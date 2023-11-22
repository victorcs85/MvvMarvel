package br.com.android.victorcs.mvvmarvel.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenericItem(
    val resourceUri: String?,
    val name: String?,
    val type: String?
): Parcelable
