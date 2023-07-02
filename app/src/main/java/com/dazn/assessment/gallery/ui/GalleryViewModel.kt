package com.dazn.assessment.gallery.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dazn.assessment.gallery.data.model.GalleryDataResult
import com.dazn.assessment.gallery.data.model.ImageInfo
import com.dazn.assessment.gallery.data.repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository:GalleryRepository) : ViewModel() {

    val galleryImages = MutableLiveData<List<ImageInfo>?>()
    val showError = MutableLiveData<String?>()
    val showLoading = MutableLiveData<Boolean>()

    fun loadGallery() {
        //show loading status
        showLoading.value = true

        // launch coroutine
        viewModelScope.launch(Dispatchers.IO) {

            // fetch/load images
            val galleryDataResult = repository.getImages()

            // response (success/failure) ready, hide loading indicator
            showLoading.postValue(false)

            // notify UI using live data based on response status
            when(galleryDataResult){
                is GalleryDataResult.Success -> {
                    // when result success, inform UI to hide the error view
                    showError.postValue(null)

                    // notify UI to update gallery images
                    galleryImages.postValue(galleryDataResult.data) }
                is GalleryDataResult.Error -> {
                    // notify UI to show error
                    showError.postValue(galleryDataResult.exception.message)
                }
            }

        }
    }

}