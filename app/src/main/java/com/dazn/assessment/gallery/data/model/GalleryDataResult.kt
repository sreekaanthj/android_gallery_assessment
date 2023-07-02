package com.dazn.assessment.gallery.data.model

sealed class GalleryDataResult<out R> {
    data class Success<out T>(val data: T) : GalleryDataResult<T>()
    data class Error(val exception: Exception) : GalleryDataResult<Nothing>()
}
