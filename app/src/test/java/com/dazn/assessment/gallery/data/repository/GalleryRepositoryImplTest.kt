package com.dazn.assessment.gallery.data.repository

import com.dazn.assessment.gallery.data.datasource.GalleryDataSource
import com.dazn.assessment.gallery.data.model.GalleryDataResult
import com.dazn.assessment.gallery.data.model.ImageInfo
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Test

class GalleryRepositoryImplTest {

    @Test
    fun getImages() = runTest {

        // fake dependency
        val galleryDataSource: GalleryDataSource = FakeGalleryDataSource()

        // subject under test GalleryRepositoryImpl
        when (val images = GalleryRepositoryImpl(galleryDataSource).getImages()) {
            is GalleryDataResult.Success -> {
                assertSame("2022-01-01", images.data[0].date)
            }

            else -> {
                fail()
            }
        }

    }


    class FakeGalleryDataSource : GalleryDataSource {

        override suspend fun getImages(): GalleryDataResult<List<ImageInfo>> {
            return GalleryDataResult.Success(
                listOf(
                    ImageInfo(null, "2022-01-01"),
                    ImageInfo(null, "2022-01-03"),
                    ImageInfo(null, "2022-01-10"),
                    ImageInfo(null, "2022-01-06"),
                    ImageInfo(null, "2022-01-05"),
                    ImageInfo(null, "2022-01-02")

                )
            )
        }

    }

}