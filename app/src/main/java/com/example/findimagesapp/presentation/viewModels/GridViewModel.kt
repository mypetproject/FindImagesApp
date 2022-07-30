package com.example.findimagesapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findimagesapp.domain.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor(val repositoryImpl: RepositoryImpl) : ViewModel() {

    val imagesListFlow = repositoryImpl.searchResponse

    fun downloadAdditionalData() {
        if (repositoryImpl.isFreeForDownload.value)
            viewModelScope.launch {
                repositoryImpl.downloadAdditionalData()
            }
    }
}