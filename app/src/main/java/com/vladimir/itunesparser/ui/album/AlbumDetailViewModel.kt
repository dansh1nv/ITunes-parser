package com.vladimir.itunesparser.ui.album

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladimir.itunesparser.data.models.AlbumDetail
import com.vladimir.itunesparser.data.network.ApiFactory
import com.vladimir.itunesparser.data.repository.AlbumDetailRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class AlbumDetailViewModel : ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository: AlbumDetailRepository =
        AlbumDetailRepository(ApiFactory.itunesApi)

    val trackLiveData = MutableLiveData<List<AlbumDetail>>()
    val albumDetailLiveData = MutableLiveData<List<AlbumDetail>>()

    fun fetchAlbumDetail(collectionId: String) {

        scope.launch {
            try {
                val tracks = repository.getAlbumDetail(collectionId)?.filter {
                    it.wrapperType == "track"
                }
                trackLiveData.postValue(tracks)
                val albumDetail = repository.getAlbumDetail(collectionId)?.filter {
                    it.wrapperType == "collection"
                }
                albumDetailLiveData.postValue(albumDetail)
            } catch (e: Throwable) {
                "Error! ${e.message}"
            }

        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}