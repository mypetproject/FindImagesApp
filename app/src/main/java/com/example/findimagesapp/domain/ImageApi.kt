package com.example.findimagesapp.domain

import com.example.findimagesapp.dataModels.ImagesResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

    @GET("search.json?tbm=isch&api_key=0838e7ae1402e544a8980552b72a1d012932ae8e573a7be4718c52ee27104c36")
    fun getImages(@Query("q") query: String, @Query("ijn") page: Int): Call<ImagesResults>
}