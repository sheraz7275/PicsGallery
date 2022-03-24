package com.example.picgallery.dataSource.remote

import com.example.picgallery.dataModel.Images
import com.example.picgallery.dataModel.Photos
import kotlinx.coroutines.Deferred
import org.junit.runners.Parameterized
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RestService {

    @GET("services/rest/")
    fun getImagesAsync(@QueryMap options: Map<String, String>)
            : Deferred<Images>
}
