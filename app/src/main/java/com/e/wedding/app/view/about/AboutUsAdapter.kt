package com.e.wedding.app.view.about

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.wedding.app.model.Image
import com.e.wedding.databinding.AboutUsRecyclerItemBinding

class AboutUsAdapter(private val context: Context, private val images: List<Image>) :
    RecyclerView.Adapter<AboutUsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AboutUsRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) = with(binding) {
            Glide.with(context)
                .load(image.url)
                .into(aboutUsItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutUsAdapter.ViewHolder {
        return ViewHolder(AboutUsRecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: AboutUsAdapter.ViewHolder, position: Int) {
        val images = images[position]
        holder.bind(images)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}