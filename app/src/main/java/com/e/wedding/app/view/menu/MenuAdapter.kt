package com.e.wedding.app.view.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.wedding.app.model.Image
import com.e.wedding.databinding.RecyclerMenuItemBinding

class MenuAdapter(private val context: Context, private val images: List<Image>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RecyclerMenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) = with(binding) {
            Glide.with(context)
                .load(image.url)
                .into(menuItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        return ViewHolder(RecyclerMenuItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        val images = images[position]
        holder.bind(images)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}