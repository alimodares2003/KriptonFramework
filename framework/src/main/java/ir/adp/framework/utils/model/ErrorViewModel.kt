package ir.adp.club.utils.model

import com.google.gson.annotations.SerializedName

data class ErrorViewModel(
    @SerializedName("drawable") val drawable: Int,
    @SerializedName("title") val titleRes: Int,
    @SerializedName("description") val descriptionRes: Int
)
