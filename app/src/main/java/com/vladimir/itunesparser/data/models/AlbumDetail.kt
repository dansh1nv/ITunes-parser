package com.vladimir.itunesparser.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumDetail(
    @Json(name="wrapperType")
    val wrapperType: String?,
    /*Информация об альбоме*/
    @Json(name = "artworkUrl100")
    val artworkUrl: String?,  //Ссылка на обложку альбома
    @Json(name = "collectionId")
    val collectionId: Int?,
    @Json(name = "collectionName")
    val albumName: String?, //Название альбома
    @Json(name = "copyright")
    val copyright: String?, //Копирайт
    @Json(name = "primaryGenreName")
    val genreName: String?, //Жанр
    @Json(name = "releaseDate")
    val releaseDate: String?,//Дата релиза
    @Json(name = "artistName")
    val artistName: String?, //Название исполнителя
    /*Информация о треках*/
    @Json(name = "trackName")
    val trackName: String?, //название трека
    @Json(name = "trackNumber")
    val trackNumber: Int?, //номер трека
    @Json(name = "trackTimeMillis")
    val trackTimeMillis: Int?, //длительность трека
    @Json(name = "discCount")
    val discCount: Int?, //Число дисков
    @Json(name = "discNumber")
    val discNumber: Int? //Номер диска
)