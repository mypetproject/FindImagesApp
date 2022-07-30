package com.example.findimagesapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.findimagesapp.domain.RepositoryImpl
import com.example.findimagesapp.presentation.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsBottomSheetViewModel @Inject constructor(repositoryImpl: RepositoryImpl): ViewModel() {

    private val imagesList = repositoryImpl.searchResponse

    fun getTitle() = imagesList.value[MainActivity.currentPosition].title
    fun getLink() = imagesList.value[MainActivity.currentPosition].link
}