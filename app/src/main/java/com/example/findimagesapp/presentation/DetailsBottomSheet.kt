package com.example.findimagesapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.findimagesapp.R
import com.example.findimagesapp.databinding.BottomSheetDetailsBinding
import com.example.findimagesapp.presentation.viewModels.DetailsBottomSheetViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment with search result details
 *
 * @author S. Kishkar
 */
@AndroidEntryPoint
class DetailsBottomSheet : BottomSheetDialogFragment() {

    private val model: DetailsBottomSheetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<BottomSheetDetailsBinding>(
        inflater,
        R.layout.bottom_sheet_details,
        container,
        false
    ).apply {
        title.text = model.getTitle()
        link.text = model.getLink()
    }.root

    companion object {
        /**
         * DetailsBottomSheet fragment tag
         */
        const val TAG = "DetailsBottomSheet"
    }
}