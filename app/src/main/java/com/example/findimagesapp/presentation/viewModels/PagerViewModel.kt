package com.example.findimagesapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.findimagesapp.domain.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View model for Pager fragment
 *
 * @param repositoryImpl a repository for working with a search service
 *
 * @author S. Kishkar
 */
@HiltViewModel
class PagerViewModel @Inject constructor(repositoryImpl: RepositoryImpl) : ViewModel() {
    val imagesListFlow = repositoryImpl.searchResponse

    fun getCurrentUrl(currentItem: Int): String =
        imagesListFlow.value[currentItem].link
}