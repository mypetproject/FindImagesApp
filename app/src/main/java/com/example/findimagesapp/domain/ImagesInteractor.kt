package com.example.findimagesapp.domain

import com.example.findimagesapp.dataModels.ImagesResults
import retrofit2.Call

interface ImagesInteractor {
    fun getImages(query: String, page: Int): Call<ImagesResults>
}