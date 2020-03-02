package com.vladimir.itunesparser.data.repository

import com.vladimir.itunesparser.data.models.AlbumDetail
import com.vladimir.itunesparser.data.network.ITunesApi
import com.vladimir.testtask.utils.Constants

class AlbumDetailRepository(private val api: ITunesApi) : BaseRepository() {

    suspend fun getAlbumDetail(collectionId: String): MutableList<AlbumDetail>? {
        val albumDetailResponse = safeApiCall(
            call = { api.getAlbumDetailInformation(collectionId, Constants.SONG).await() },
            errorMessage = "Error Fetching Album Detail"
        )
        return albumDetailResponse?.albumDetails?.toMutableList()
    }
}