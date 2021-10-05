package com.e.wedding.app.view.gallery

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.e.wedding.R
import com.e.wedding.app.model.Image
import com.e.wedding.databinding.ViewGalleryItemBinding
import com.e.wedding.databinding.ViewSlideShowItemBinding

class GalleryAdapter(
    private val context: Context,
    private val imageClick: (image: Image) -> Unit,
    private val imageLongClick: (image: Image) -> Unit
) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private val images = mutableListOf<Image>()
    private val diff = GalleryDiffUtil()
    private var mode = GalleryMode.SlideShow

    sealed class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(image: Image)

        protected fun setListener(image: Image, imageClick: (image: Image) -> Unit, imageLongClick: (image: Image) -> Unit) {
            binding.root.setOnClickListener { imageClick.invoke(image) }
            binding.root.setOnLongClickListener {
                imageLongClick.invoke(image)
                return@setOnLongClickListener true
            }
        }
    }

    inner class GalleryViewHolder(val binding: ViewGalleryItemBinding) : ViewHolder(binding) {
        private val options = RequestOptions()
            .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.colorAccent)))
            .error(ColorDrawable(ContextCompat.getColor(context, R.color.colorPrimary)))

        override fun bind(image: Image) {
            setListener(image, imageClick, imageLongClick)
            Glide.with(context)
                .load(image.url)
                .apply(options)
                .into(binding.ivImage)
        }
    }

    inner class SlideShowViewHolder(val binding: ViewSlideShowItemBinding) : ViewHolder(binding) {
        private val options = RequestOptions()
            .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.colorAccent)))
            .error(ColorDrawable(ContextCompat.getColor(context, R.color.colorPrimary)))

        override fun bind(image: Image) {
            setListener(image, imageClick, imageLongClick)
            Glide.with(context)
                .load(image.url)
                .apply(options)
                .into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (mode) {
            GalleryMode.SlideShow -> SlideShowViewHolder(ViewSlideShowItemBinding.inflate(LayoutInflater.from(context), parent, false))
            GalleryMode.Gallery -> GalleryViewHolder(ViewGalleryItemBinding.inflate(LayoutInflater.from(context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val images = images[position]
        holder.bind(images)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun updateMode(mode: GalleryMode) {
        //val result = diff.updateMode(this.mode, mode)
        this.mode = mode
        notifyDataSetChanged()
        //result.dispatchUpdatesTo(this)
    }

    fun updateImages(newImages: List<Image>) {
        val result = diff.updateImages(images, newImages)
        images.clear()
        images.addAll(newImages)
        result.dispatchUpdatesTo(this)
    }
}

enum class GalleryMode {
    SlideShow,
    Gallery
}

