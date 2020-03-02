package com.vladimir.itunesparser.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladimir.itunesparser.data.models.Album
import com.vladimir.itunesparser.data.network.ApiFactory
import com.vladimir.itunesparser.data.repository.AlbumRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AlbumListViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository: AlbumRepository =
        AlbumRepository(ApiFactory.itunesApi)

    val albumLiveData = MutableLiveData<MutableList<Album>>()

    fun fetchAlbums(query: String) {
        scope.launch {
            try {
                val albums = repository.getAlbums(query)
                albumLiveData.postValue(albums)
            } catch (e: Throwable) {
                "Error! ${e.message}"
            }
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}