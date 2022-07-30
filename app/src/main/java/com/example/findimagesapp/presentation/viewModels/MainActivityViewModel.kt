package com.example.findimagesapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.findimagesapp.domain.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(val repositoryImpl: RepositoryImpl) : ViewModel() {

    fun downloadData(query: String) {
        if (query.isNotBlank()) repositoryImpl.downloadDataFirstTime(query)
    }
}