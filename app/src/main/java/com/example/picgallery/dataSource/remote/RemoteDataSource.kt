package com.example.picgallery.dataSource.remote

import com.example.picgallery.dataModel.Images
import com.example.picgallery.dataSource.ImageDataSource
import com.example.picgallery.utils.AppConst.API_KEY
import com.example.picgallery.utils.AppConst.DATA_PER_PAGE
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val restApi: RestService) : ImageDataSource {

    override suspend fun searchImages(query: String, page: String, pageSize: String): Images {
        val option: HashMap<String, String> = HashMap()
        option.put("method", "flickr.photos.search")
        option.put("api_key", API_KEY)
        option.put("format", "json")
        option.put("nojsoncallback", "1")
        option.put("safe_search", "2")
        option.put("text", query)
        option.put("per_page", pageSize)
        option.put("page", page)
        return restApi.getImagesAsync(option).await()
    }

}