package com.example.picgallery.repository

import androidx.paging.PagingData
import com.example.picgallery.dataModel.Photo
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun getImageList(query: String): Flow<PagingData<Photo>>

}