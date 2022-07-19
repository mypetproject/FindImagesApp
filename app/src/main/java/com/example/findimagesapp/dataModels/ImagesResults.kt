package com.example.findimagesapp.dataModels

import com.google.gson.annotations.SerializedName

data class ImagesResults(
    @SerializedName("images_results")
    val imageResults: List<ImageResult>
)