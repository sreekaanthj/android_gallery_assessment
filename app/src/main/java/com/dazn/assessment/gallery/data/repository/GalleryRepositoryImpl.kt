package com.dazn.assessment.gallery.data.repository

import com.dazn.assessment.gallery.data.datasource.GalleryDataSource
import com.dazn.assessment.gallery.data.model.GalleryDataResult
import com.dazn.assessment.gallery.data.model.ImageInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(private val dataSource: GalleryDataSource) : GalleryRepository {
    /*
    Move the execution of the coroutine to the I/O dispatcher, using withContext(Dispatchers.IO)
     */
    override suspend fun getImages(): GalleryDataResult<List<ImageInfo>> = withContext(Dispatchers.IO){dataSource.getImages()}
}