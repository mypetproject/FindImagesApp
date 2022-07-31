package com.example.findimagesapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.findimagesapp.domain.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View model for MainActivity
 *
 * @param repositoryImpl a repository for working with a search service
 *
 * @author S. Kishkar
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(val repositoryImpl: RepositoryImpl) : ViewModel() {

    val failureResponse = repositoryImpl.failureResponse
    var query = ""

    fun downloadData(query: String) {
        this.query = query
        if (query.isNotBlank()) repositoryImpl.downloadDataFirstTime(query)
    }
}