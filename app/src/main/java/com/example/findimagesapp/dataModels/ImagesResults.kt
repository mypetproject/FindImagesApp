package com.example.findimagesapp.dataModels

import com.google.gson.annotations.SerializedName

/**
 * Search query response
 *
 * @property imagesResults list of search results
 *
 * @author S. Kishkar
 */
data class ImagesResults(
    @SerializedName("images_results")
    val imagesResults: List<ImageResult>
)