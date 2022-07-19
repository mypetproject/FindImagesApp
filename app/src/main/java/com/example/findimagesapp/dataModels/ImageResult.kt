package com.example.findimagesapp.dataModels

import com.google.gson.annotations.SerializedName

data class ImageResult(
    @SerializedName("position")
    val position: String,
    @SerializedName("thumbnail")
    val thumbnailLink: String,
    @SerializedName("original")
    val originalSizeLink: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String
)