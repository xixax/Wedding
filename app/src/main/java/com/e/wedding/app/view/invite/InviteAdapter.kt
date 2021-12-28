package com.e.wedding.app.view.invite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.wedding.app.model.Image
import com.e.wedding.databinding.InviteRecyclerItemBinding

class InviteAdapter(private val context: Context, private val images: List<Image>) : RecyclerView.Adapter<InviteAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: InviteRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) = with(binding) {
            Glide.with(context)
                .load(image.url)
                .into(inviteItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteAdapter.ViewHolder {
        return ViewHolder(InviteRecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: InviteAdapter.ViewHolder, position: Int) {
        val images = images[position]
        holder.bind(images)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}