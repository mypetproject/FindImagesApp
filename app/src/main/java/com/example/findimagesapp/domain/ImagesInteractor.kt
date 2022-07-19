package com.example.findimagesapp.domain

import com.example.findimagesapp.dataModels.ImagesResults
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImagesInteractor {

    fun getNote(): Call<ImagesResults> = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()
            )
        )
        .build().create(ImageApi::class.java).getNote()

    companion object {
        private const val BASE_URL =
            "https://serpapi.com/"
            //"https://serpapi.com/search.json/"
    }
}