package com.example.picgallery.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.picgallery.dataModel.Photo
import com.example.picgallery.dataSource.ImageDataSource
import java.io.IOException


open class ImagePagingData(private val dataSource: ImageDataSource, private val query: String) : PagingSource<Int, Photo>() {

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: 1

        return try {
            val resp =
                dataSource.searchImages(query, position.toString(), params.loadSize.toString())
            val imagesList = resp.photos.photo

            LoadResult.Page(
                imagesList,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (imagesList.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


}