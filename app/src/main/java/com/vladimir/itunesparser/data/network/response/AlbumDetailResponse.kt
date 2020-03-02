package com.vladimir.itunesparser.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vladimir.itunesparser.data.models.AlbumDetail

@JsonClass(generateAdapter = true)
data class AlbumDetailResponse(
    @Json(name = "results")
    val albumDetails: List<AlbumDetail>
)