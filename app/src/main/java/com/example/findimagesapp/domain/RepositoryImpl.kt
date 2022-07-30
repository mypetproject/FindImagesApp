package com.example.findimagesapp.domain

import com.example.findimagesapp.dataModels.ImageResult
import com.example.findimagesapp.dataModels.ImagesResults
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val interactorImpl: ImagesInteractorImpl) :
    Repository {

    val searchResponse: StateFlow<List<ImageResult>>
        get() = _searchResponse

    private val _searchResponse = MutableStateFlow<List<ImageResult>>(listOf())

    val isFreeForDownload: StateFlow<Boolean>
        get() = _isFreeForDownload

    private val _isFreeForDownload: MutableStateFlow<Boolean> = MutableStateFlow(true)

    private var query = ""
    private var page = 0

    override fun downloadDataFirstTime(query: String) {
        this.query = query
        interactorImpl.getImages(query, 0).enqueue(object : Callback<ImagesResults> {
            override fun onResponse(call: Call<ImagesResults>, response: Response<ImagesResults>) {
                _searchResponse.value = response.body()?.imagesResults.orEmpty()
            }

            override fun onFailure(call: Call<ImagesResults>, t: Throwable) {}
        })
        page = 1
    }

    override fun downloadAdditionalData() {
        _isFreeForDownload.value = false
        interactorImpl.getImages(query, page).enqueue(object : Callback<ImagesResults> {
            override fun onResponse(call: Call<ImagesResults>, response: Response<ImagesResults>) {
                _searchResponse.value += response.body()?.imagesResults.orEmpty()
                _isFreeForDownload.value = true
            }

            override fun onFailure(call: Call<ImagesResults>, t: Throwable) {
                _isFreeForDownload.value = true
            }
        })
        page++
    }
}