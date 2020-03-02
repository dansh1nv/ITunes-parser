package com.vladimir.itunesparser.data.network

import com.vladimir.itunesparser.data.network.response.AlbumResponse
import com.vladimir.itunesparser.data.network.response.AlbumDetailResponse
import retrofit2.Response
import retrofit2.http.*
import kotlinx.coroutines.Deferred

interface ITunesApi {

    /*https://itunes.apple.com/search?term=2+маши&media=music&entity=album*/

    //Получение альбома
    @GET("/search")
    fun getAlbumInformation(
        @Query("term") artistName: String,
        @Query("media") media: String = "music",
        @Query("entity") entity: String = "album"
    ): Deferred<Response<AlbumResponse>>

    /*https://itunes.apple.com/lookup?id=1440650816&entity=song*/

    //Получение подробной информации об альбоме
    @GET("/lookup")
    fun getAlbumDetailInformation(
        @Query("id") albumId: String,
        @Query("entity") entity: String = "song"
    ): Deferred<Response<AlbumDetailResponse>>

}

