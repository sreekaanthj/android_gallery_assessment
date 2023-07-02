package com.dazn.assessment.gallery.di

import com.dazn.assessment.gallery.data.datasource.GalleryDataSource
import com.dazn.assessment.gallery.data.datasource.LocalJsonGalleryDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class GalleryJsonDataSourceModule {

    @Provides
    @Named("localJsonFileName")
    fun jsonFileName() = "nasa_details.json"


}


@Module
@InstallIn(SingletonComponent::class)
interface GalleryDataSourceModule {

    @Binds
    fun dataSource(localJsonGalleryDataSource: LocalJsonGalleryDataSource): GalleryDataSource
}
