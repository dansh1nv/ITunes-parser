package com.vladimir.itunesparser.data.repository

import com.vladimir.itunesparser.data.models.AlbumDetail
import com.vladimir.itunesparser.data.models.Track
import com.vladimir.itunesparser.data.network.ITunesApi
import com.vladimir.testtask.utils.Constants
import com.vladimir.testtask.utils.Constants.TRACK

class AlbumDetailRepository(private val api: ITunesApi) : BaseRepository() {

    suspend fun getAlbumDetail(collectionId: String): MutableList<AlbumDetail>? {
        val albumDetailResponse = safeApiCall(
            call = { api.getAlbumDetailInformation(collectionId, Constants.SONG).await() },
            errorMessage = "Error Fetching Album Detail"
        )
        return albumDetailResponse?.albumDetails?.toMutableList()
    }

    suspend fun getTrack(collectionId: String): List<Track>? {
        return getAlbumDetail(collectionId)
            ?.filter { it.wrapperType == TRACK }
            ?.map {
                Track(
                    trackName = it.trackName,
                    trackNumber = it.trackNumber,
                    trackTimeMillis = it.trackTimeMillis,
                    artistName = it.artistName
                )
            }
    }
}