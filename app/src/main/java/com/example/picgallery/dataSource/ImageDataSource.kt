package com.example.picgallery.dataSource

import com.example.picgallery.dataModel.Images
import com.example.picgallery.dataModel.Photos

interface ImageDataSource {
    suspend fun searchImages(query: String, page: String, pageSize: String): Images
}