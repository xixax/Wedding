package com.e.wedding.app.view.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.e.wedding.app.model.DataHolder
import com.e.wedding.app.model.Image

private const val TAG = "ImageViewModel"

class ImageViewModel(private val navController: NavController) : ViewModel() {
    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> = _images

    private val _imageSelected = MutableLiveData<Image>()
    val imageSelected: LiveData<Image> = _imageSelected

    fun getImages() {
        val images = DataHolder.getGuestLoggedIn()?.galeriaImages
        _images.value = images?.map { Image(it) } ?: emptyList()
    }

    fun imageClick(image: Image) {
        _imageSelected.value = image
        navController.navigate(GalleryFragmentDirections.actionNavGalleryToNavSlideshow())
    }
}