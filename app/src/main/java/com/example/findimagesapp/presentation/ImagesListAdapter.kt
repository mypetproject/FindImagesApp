package com.example.findimagesapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.findimagesapp.databinding.ItemImagesRecyclerviewBinding

/**
 * Adapter for thumbnail list
 *
 * @author S. Kishkar
 */
class ImagesListAdapter(
    private var imageResultList: List<String>,
    private val onItemClick: ((Int) -> Unit)
) : RecyclerView.Adapter<ImagesListAdapter.ImageViewHolder>() {

    /**
     * Method for setting a new list of URLs
     *
     * @param images list of URLs
     */
    fun setImageResultList(images: List<String>) {
        imageResultList = images
    }

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

        private val placeholder = CircularProgressDrawable(binding.imageView.context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

        fun bind(imageResult: String, onItemClick: (Int) -> Unit) {
            Glide.with(binding.imageView.context)
                .load(imageResult)
                .placeholder(placeholder)
                .into(binding.imageView)
            itemView.setOnClickListener { onItemClick.invoke(absoluteAdapterPosition) }
        }
    }
}