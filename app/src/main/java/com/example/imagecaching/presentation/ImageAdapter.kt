package com.example.imagecaching.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.imagecaching.R
import com.example.imagecaching.databinding.ImageItemBinding
import com.example.imagecaching.domain.model.Media
import com.example.imageloading.presentation.Comage

class ImageAdapter(
    private val context: Context,
    private var images: List<Media>
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    fun update(images: List<Media>) {
        this.images = images
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(media: Media) {
            Comage.loadImage(
                imageView = binding.imageView,
                imageUrl = media.imageUrl,
                placeHolder = AppCompatResources.getDrawable(context, R.drawable.placeholder)
            )
        }
    }
}