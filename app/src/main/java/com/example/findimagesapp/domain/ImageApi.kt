package com.example.findimagesapp.domain

import com.example.findimagesapp.dataModels.ImagesResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API for working with a search request
 *
 * @author S. Kishkar
 */
interface ImageApi {

    /**
     * Get search results
     *
     * @param query search keyword
     * @param page search page
     * @return [Call] with search results
     *
     * @author S. Kishkar
     */
    @GET("search.json?tbm=isch&api_key=879f5f8cd45cf1504ae99988cadcd7b7d566516b9132c36e58605288bc59863a")
    fun getImages(@Query("q") query: String, @Query("ijn") page: Int): Call<ImagesResults>
}