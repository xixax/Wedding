package com.e.wedding.app.view.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.wedding.R
import com.e.wedding.app.model.Image
import kotlinx.android.synthetic.main.menu_recycler_item.view.*

class MenuAdapter(private val context: Context, private val images: List<Image>)
    : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(image: Image){
            Glide.with(context)
                .load(image.url)
                .into(itemView.menu_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.menu_recycler_item,parent,false))
    }

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        val images = images[position]
        holder.bind(images)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}