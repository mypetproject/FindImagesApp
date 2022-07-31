package com.example.findimagesapp.dataModels

import com.google.gson.annotations.SerializedName

/**
 * Search result element
 *
 * @property thumbnailLink link to thumbnail
 * @property originalSizeLink link to image in original size
 * @property title image title
 * @property link link to webpage
 *
 * @author S. Kishkar
 */
data class ImageResult(
    @SerializedName("thumbnail")
    val thumbnailLink: String,
    @SerializedName("original")
    val originalSizeLink: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String
)