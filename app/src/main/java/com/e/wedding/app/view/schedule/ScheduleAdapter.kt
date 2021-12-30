package com.e.wedding.app.view.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.wedding.app.model.Image
import com.e.wedding.databinding.RecyclerScheduleItemBinding

class ScheduleAdapter(private val context: Context, private val images: List<Image>) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RecyclerScheduleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) = with(binding) {
            Glide.with(context)
                .load(image.url)
                .into(scheduleItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        return ViewHolder(RecyclerScheduleItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val images = images[position]
        holder.bind(images)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}