package com.example.picgallery.dataSource

import com.example.picgallery.dataModel.Images
import com.example.picgallery.dataModel.Photo
import com.example.picgallery.dataModel.Photos

class FakeDataSource:ImageDataSource {
    override suspend fun searchImages(query: String, page: String, pageSize: String): Images {

        val fakeImageList:MutableList<Photo> = mutableListOf()

        val photo=Photo(1234,"123415",1,1,1,"Joe","12345","34212","Nature")
        for (x in 1..10){
            fakeImageList.add(photo)
        }
        val photos=Photos(1,5,20, photo = listOf(photo),20)


        return Images(photos,"Active")

    }
}