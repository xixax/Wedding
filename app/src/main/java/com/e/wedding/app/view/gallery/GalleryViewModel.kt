package com.e.wedding.app.view.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.wedding.app.model.Image

class GalleryViewModel : ViewModel() {

    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> = _images

    private val _mode = MutableLiveData(GalleryMode.Gallery)
    val mode: LiveData<GalleryMode> = _mode

    init {
        _images.value = getImages()
    }

    private fun getImages(): List<Image> {
        val images = mutableListOf<Image>()
        for (i in 0..1000) {
            images.add(Image("https://picsum.photos/id/$i/1000/1000"))
        }
        return images
    }

    fun imageClick(image: Image) {
        _mode.value = GalleryMode.SlideShow
    }

    fun imageLongClick(image: Image) {

    }
}