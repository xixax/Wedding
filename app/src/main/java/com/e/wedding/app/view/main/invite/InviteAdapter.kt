package com.e.wedding.app.view.main.invite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.wedding.R
import com.e.wedding.app.model.Image
import kotlinx.android.synthetic.main.invite_recycler_item.view.*

class InviteAdapter(private val context: Context, private val images: List<Image>)
    : RecyclerView.Adapter<InviteAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(image: Image){
            Glide.with(context)
                .load(image.url)
                .into(itemView.invite_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.invite_recycler_item,parent,false))
    }

    override fun onBindViewHolder(holder: InviteAdapter.ViewHolder, position: Int) {
        val images = images[position]
        holder.bind(images)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}