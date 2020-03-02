package com.vladimir.itunesparser.data.repository

import com.vladimir.itunesparser.data.models.Album
import com.vladimir.itunesparser.data.network.ITunesApi
import com.vladimir.testtask.utils.Constants

class AlbumRepository(private val api: ITunesApi) : BaseRepository() {

    suspend fun getAlbums(query: String): MutableList<Album>? {
        val albumResponse = safeApiCall(
            call = { api.getAlbumInformation(query, Constants.MEDIA, Constants.ENTITY).await() },
            errorMessage = "Error Fetching Albums"
        )
        return albumResponse?.albums?.toMutableList()
    }
}