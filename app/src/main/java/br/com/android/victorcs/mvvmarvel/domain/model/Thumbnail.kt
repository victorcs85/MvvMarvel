package br.com.android.victorcs.mvvmarvel.domain.model

import android.os.Parcelable
import br.com.android.victorcs.mvvmarvel.presentation.utils.ImageVariantSizeEnum
import kotlinx.parcelize.Parcelize

private const val EMPTY = ""
private const val BAR = "/"
private const val DOT = "."

@Parcelize
data class Thumbnail(
    val extension: String?,
    val path: String?
) : Parcelable {
    val pathSmall: String
        get() = getFullPathById(ImageVariantSizeEnum.PORTRAIT_SMALL)

    val pathMedium: String
        get() = getFullPathById(ImageVariantSizeEnum.PORTRAIT_MEDIUM)

    val pathXLarge: String
        get() = getFullPathById(ImageVariantSizeEnum.PORTRAIT_XLARGE)

    val pathFantastic: String
        get() = getFullPathById(ImageVariantSizeEnum.PORTRAIT_FANTASTIC)

    val pathUncanny: String
        get() = getFullPathById(ImageVariantSizeEnum.PORTRAIT_UNCANNY)

    val pathIncredible: String
        get() = getFullPathById(ImageVariantSizeEnum.PORTRAIT_INCREDIBLE)


    private fun getFullPathById(imageVariant: ImageVariantSizeEnum): String =
        path?.plus(BAR)?.plus(imageVariant.value)?.plus(DOT)?.plus(extension.orEmpty()) ?: EMPTY
}