package com.dazn.assessment.gallery.di

import com.dazn.assessment.gallery.data.repository.GalleryRepository
import com.dazn.assessment.gallery.data.repository.GalleryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun galleryRepository(galleryRepositoryImpl: GalleryRepositoryImpl):GalleryRepository
}