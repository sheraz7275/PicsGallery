package com.example.picgallery.dataModel


import com.squareup.moshi.Json

data class Images(
    @Json(name = "photos")
    val photos: Photos,
    @Json(name = "stat")
    val stat: String?
)

data class Photo(
    @Json(name = "farm")
    val farm: Int?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "isfamily")
    val isfamily: Int?,
    @Json(name = "isfriend")
    val isfriend: Int?,
    @Json(name = "ispublic")
    val ispublic: Int?,
    @Json(name = "owner")
    val owner: String?,
    @Json(name = "secret")
    val secret: String?,
    @Json(name = "server")
    val server: String?,
    @Json(name = "title")
    val title: String?
)

data class Photos(
    @Json(name = "page")
    val page: Int?,
    @Json(name = "pages")
    val pages: Int?,
    @Json(name = "perpage")
    val perpage: Int?,
    @Json(name = "photo")
    val photo: List<Photo>,
    @Json(name = "total")
    val total: Int?
)