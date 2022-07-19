package com.example.findimagesapp.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.findimagesapp.databinding.ItemImagesRecyclerviewBinding

class ImagesListAdapter(
    //private var imageResultList: List<ImageResult>
    private var imageResultList: List<String>,
    private val onItemClick: ((View, Int) -> Unit))
    : RecyclerView.Adapter<ImagesListAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        ItemImagesRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .also {
                return ImageViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageResultList[position], onItemClick)
    }

    override fun getItemCount(): Int = imageResultList.size

    class ImageViewHolder(private val binding: ItemImagesRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageResult: String, onItemClick: (View, Int) -> Unit) {
           // binding.imageView
            //Log.d("myLogs", "bind imageResult: ${imageResult.position}")
            Log.d("myLogs", "bind imageResult: $imageResult")
            Glide.with(binding.imageView.context).load(imageResult).into(binding.imageView)
            itemView.setOnClickListener { onItemClick.invoke(itemView, absoluteAdapterPosition) }
            itemView.transitionName = imageResult.hashCode().toString()
        }
    }
}