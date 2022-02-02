package br.com.android.victorcs.mvvmarvel.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
    val extension: String?,
    val path: String?
):Parcelable