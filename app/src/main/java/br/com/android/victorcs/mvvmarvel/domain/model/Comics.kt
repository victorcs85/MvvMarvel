package br.com.android.victorcs.mvvmarvel.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comics(
    val available: Int?,
    val collectionURI: String?,
    val items: List<GenericItem>?,
    val returned: Int?
): Parcelable