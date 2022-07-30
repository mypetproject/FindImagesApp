package com.example.findimagesapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.findimagesapp.domain.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FullImageViewModel @Inject constructor(repositoryImpl: RepositoryImpl): ViewModel() {

    private val imagesList = repositoryImpl.searchResponse

    fun getOriginalImageSizeLink(position: Int) =
        imagesList.value[position].originalSizeLink

    fun getThumbnailImageSizeLink(position: Int) =
        imagesList.value[position].thumbnailLink
}