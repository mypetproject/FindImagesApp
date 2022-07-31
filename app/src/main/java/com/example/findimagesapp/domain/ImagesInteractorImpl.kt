package com.example.findimagesapp.domain

import com.example.findimagesapp.dataModels.ImagesResults
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * Implementing of an interactor for working with a search service
 *
 * @author S. Kishkar
 */
class ImagesInteractorImpl @Inject constructor() : ImagesInteractor {

    override fun getImages(query: String, page: Int): Call<ImagesResults> = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()
            )
        )
        .build().create(ImageApi::class.java).getImages(query, page)

    companion object {
        private const val BASE_URL = "https://serpapi.com/"
    }
}