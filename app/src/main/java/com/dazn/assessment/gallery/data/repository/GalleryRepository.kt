package com.dazn.assessment.gallery.data.repository

import com.dazn.assessment.gallery.data.model.GalleryDataResult
import com.dazn.assessment.gallery.data.model.ImageInfo

interface GalleryRepository {
    suspend fun getImages(): GalleryDataResult<List<ImageInfo>>
}