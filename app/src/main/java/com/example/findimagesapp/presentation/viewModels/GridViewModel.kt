package com.example.findimagesapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findimagesapp.domain.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model for Grid fragment
 *
 * @param repositoryImpl a repository for working with a search service
 *
 * @author S. Kishkar
 */
@HiltViewModel
class GridViewModel @Inject constructor(val repositoryImpl: RepositoryImpl) : ViewModel() {

    val imagesListFlow = repositoryImpl.searchResponse

    fun downloadAdditionalData() {
        if (repositoryImpl.isFreeForDownload)
            viewModelScope.launch {
                repositoryImpl.downloadAdditionalData()
            }
    }
}