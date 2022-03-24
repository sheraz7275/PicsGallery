package com.example.picgallery.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.picgallery.dataModel.Photo
import com.example.picgallery.dataSource.FakeDataSource
import com.example.picgallery.dataSource.ImageDataSource
import com.example.picgallery.utils.AppConst
import kotlinx.coroutines.flow.Flow

class FakeRepository(private val dataSource: ImageDataSource):ImageRepository {


    override suspend fun getImageList(query: String): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = AppConst.DATA_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImagePagingData(dataSource, query = query) }
        ).flow
    }
}