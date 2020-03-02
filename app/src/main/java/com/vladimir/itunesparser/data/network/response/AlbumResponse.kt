package com.vladimir.itunesparser.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vladimir.itunesparser.data.models.Album

@JsonClass(generateAdapter = true)
data class AlbumResponse(
    @Json(name = "results")
    val albums: List<Album>
)