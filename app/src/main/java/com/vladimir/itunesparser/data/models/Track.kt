package com.vladimir.itunesparser.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Track(
    val trackName: String?, //название трека
    val trackNumber: String?, //номер трека
    val trackTimeMillis: String?, //длительность трека
    val artistName: String? //Название исполнителя
)
