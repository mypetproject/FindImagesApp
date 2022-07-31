package com.example.findimagesapp.domain

import com.example.findimagesapp.dataModels.ImageResult
import com.example.findimagesapp.dataModels.ImagesResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Implementing of an repository for working with a search service
 *
 * @param interactorImpl an interactor for working with a search service
 *
 * @author S. Kishkar
 */
class RepositoryImpl @Inject constructor(private val interactorImpl: ImagesInteractorImpl) :
    Repository {

    val searchResponse: StateFlow<List<ImageResult>>
        get() = _searchResponse

    private val _searchResponse = MutableStateFlow<List<ImageResult>>(listOf())

    val isFreeForDownload: Boolean
        get() = _isFreeForDownload

    private var _isFreeForDownload: Boolean = true

    private val _failureResponse: MutableSharedFlow<String> = MutableSharedFlow(replay = 0)
    val failureResponse : SharedFlow<String>
        get() = _failureResponse.asSharedFlow()


    private var query = ""
    private var page = 0

    override fun downloadDataFirstTime(query: String) {
        this.query = query
        interactorImpl.getImages(query, 0).enqueue(object : Callback<ImagesResults> {
            override fun onResponse(call: Call<ImagesResults>, response: Response<ImagesResults>) {
                _searchResponse.value = response.body()?.imagesResults.orEmpty()
                page = 1
            }

            override fun onFailure(call: Call<ImagesResults>, t: Throwable) {
                CoroutineScope(Dispatchers.IO).launch {
                    _failureResponse.emit(SEARCH_FAILED + t)
                }
            }
        })
    }

    override fun downloadAdditionalData() {
        _isFreeForDownload = false
        interactorImpl.getImages(query, page).enqueue(object : Callback<ImagesResults> {
            override fun onResponse(call: Call<ImagesResults>, response: Response<ImagesResults>) {
                _searchResponse.value += response.body()?.imagesResults.orEmpty()
                _isFreeForDownload = true
                page++
            }

            override fun onFailure(call: Call<ImagesResults>, t: Throwable) {
                _isFreeForDownload = true
                CoroutineScope(Dispatchers.IO).launch {
                    _failureResponse.emit(SEARCH_FAILED + t)
                }
            }
        })
    }

    companion object {
        private const val SEARCH_FAILED = "Search failed\n"
    }
}