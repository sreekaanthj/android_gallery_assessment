package com.dazn.assessment.gallery.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dazn.assessment.gallery.data.model.GalleryDataResult
import com.dazn.assessment.gallery.data.model.ImageInfo
import com.dazn.assessment.gallery.data.repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository:GalleryRepository) : ViewModel() {

    private val _galleryImages = MutableLiveData<List<ImageInfo>?>()
    val galleryImages: LiveData<List<ImageInfo>?>
        get() = _galleryImages

    private val _showError = MutableLiveData<String?>()
    val showError: LiveData<String?>
        get() = _showError

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    fun loadGallery() {
        val imagesLeaded = _galleryImages.value?.size ?: 0
        if (imagesLeaded != 0) {
            Timber.v("content already loaded")
            return;
        }

        //show loading status
        _showLoading.value = true

        // launch coroutine
        viewModelScope.launch(Dispatchers.IO) {

            // fetch/load images
            val galleryDataResult = repository.getImages()

            // response (success/failure) ready, hide loading indicator
            _showLoading.postValue(false)

            // notify UI using live data based on response status
            when(galleryDataResult){
                is GalleryDataResult.Success -> {
                    // when result success, inform UI to hide the error view
                    _showError.postValue(null)

                    // notify UI to update gallery images
                    _galleryImages.postValue(galleryDataResult.data)
                }
                is GalleryDataResult.Error -> {
                    // notify UI to show error
                    _showError.postValue(galleryDataResult.exception.message)
                }
            }

        }
    }

}