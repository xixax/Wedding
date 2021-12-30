package com.e.wedding.app.view.gift

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.wedding.app.model.Image
import com.e.wedding.databinding.RecyclerGiftItemBinding

class GiftAdapter(private val context: Context, private val images: List<Image>) :
    RecyclerView.Adapter<GiftAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RecyclerGiftItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) = with(binding) {
            Glide.with(context)
                .load(image.url)
                .into(giftItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftAdapter.ViewHolder {
        return ViewHolder(RecyclerGiftItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: GiftAdapter.ViewHolder, position: Int) {
        val images = images[position]
        holder.bind(images)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}