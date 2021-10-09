package com.e.wedding.app.view.gallery

import androidx.recyclerview.widget.DiffUtil
import com.e.wedding.app.model.Image

class ImageDiffUtil : DiffUtil.Callback() {
    private var oldImages: List<Image> = listOf()
    private var newImages: List<Image> = listOf()

    fun updateImages(_oldImages: List<Image>, _newImages: List<Image>): DiffUtil.DiffResult {
        oldImages = _oldImages.toList()
        newImages = _newImages.toList()
        return DiffUtil.calculateDiff(this)
    }

    override fun getOldListSize(): Int {
        return oldImages.size
    }

    override fun getNewListSize(): Int {
        return newImages.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldImages[oldItemPosition] == newImages[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldImages[oldItemPosition].url == newImages[newItemPosition].url
    }


}