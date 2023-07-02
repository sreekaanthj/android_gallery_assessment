package com.dazn.assessment.gallery.data.model

import com.google.gson.annotations.SerializedName

data class ImageInfo(

    @SerializedName("copyright") var copyright: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("explanation") var explanation: String? = null,
    @SerializedName("hdurl") var hdurl: String? = null,
    @SerializedName("media_type") var mediaType: String? = null,
    @SerializedName("service_version") var serviceVersion: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("url") var url: String? = null

)

fun ImageInfo.fullDetails(): String {
    return "[$date] $title \n $explanation"
}
