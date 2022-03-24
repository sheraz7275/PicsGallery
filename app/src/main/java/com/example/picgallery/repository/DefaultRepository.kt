package com.example.picgallery.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.picgallery.dataModel.Photo
import com.example.picgallery.dataSource.ImageDataSource
import com.example.picgallery.utils.AppConst.DATA_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRepository @Inject constructor(private val dataSource: ImageDataSource) :
    ImageRepository {

    override suspend fun getImageList(query: String): Flow<PagingData<Photo>> {

        return Pager(
            config = PagingConfig(
                pageSize = DATA_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImagePagingData(dataSource, query = query) }
        ).flow
    }

}