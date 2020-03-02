package com.vladimir.itunesparser.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Album(
    @Json(name = "artistName")
    val artistName: String?,    //Название исполнителя
    @Json(name = "artworkUrl100")
    val artworkUrl: String?,  //Ссылка на обложку альбома
    @Json(name = "collectionId")
    val collectionId: Int?,
    @Json(name = "collectionName")
    val albumName: String? //Название альбома
)