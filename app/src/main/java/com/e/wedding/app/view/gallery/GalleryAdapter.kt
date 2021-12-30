package com.e.wedding.app.view.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.e.wedding.R
import com.e.wedding.app.base.Tagged
import com.e.wedding.app.model.Image
import com.e.wedding.app.utils.load
import com.e.wedding.databinding.ViewGalleryItemBinding

class GalleryAdapter(
    private val imageClick: (image: Image) -> Unit
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(), Tagged {

    private val images = mutableListOf<Image>()
    private val diff = ImageDiffUtil()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(ViewGalleryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val images = images[position]
        holder.bind(images)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun updateImages(newImages: List<Image>) {
        val result = diff.updateImages(images, newImages)
        images.clear()
        images.addAll(newImages)
        result.dispatchUpdatesTo(this)
    }

    inner class GalleryViewHolder(val binding: ViewGalleryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val options = RequestOptions()
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.ic_image_broken)

        fun bind(image: Image) = with(binding) {
            root.setOnClickListener { imageClick.invoke(image) }
            ivImage.load(image.url, options)
        }
    }
}

