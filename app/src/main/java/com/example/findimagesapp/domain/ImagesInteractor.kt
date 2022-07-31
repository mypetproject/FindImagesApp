package com.example.findimagesapp.domain

import com.example.findimagesapp.dataModels.ImagesResults
import retrofit2.Call

/**
 * Interactor for working with a search service
 *
 * @author S. Kishkar
 */
interface ImagesInteractor {

    /**
     * Return search response results
     *
     * @param query search keyword
     * @param page search page
     */
    fun getImages(query: String, page: Int): Call<ImagesResults>
}