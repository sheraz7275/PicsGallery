package com.example.picgallery.ui

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.picgallery.dataModel.Photo
import com.example.picgallery.databinding.ImageItemBinding
import javax.inject.Inject

class ImageAdapter @Inject constructor() :
    PagingDataAdapter<Photo, ImageAdapter.ImageViewHolder>(Diff) {

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val itemData = getItem(position)
        if (itemData != null) {
            holder.bind(itemData)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ImageItemBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(itemBinding)
    }

    class ImageViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Photo) {
            binding.mDataModel = data
        }
    }

    object Diff : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem == newItem
    }
}

