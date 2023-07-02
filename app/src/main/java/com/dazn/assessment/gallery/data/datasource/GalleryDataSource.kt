package com.dazn.assessment.gallery.data.datasource

import com.dazn.assessment.gallery.data.model.GalleryDataResult
import com.dazn.assessment.gallery.data.model.ImageInfo

interface GalleryDataSource {
    suspend fun getImages(): GalleryDataResult<List<ImageInfo>>
}