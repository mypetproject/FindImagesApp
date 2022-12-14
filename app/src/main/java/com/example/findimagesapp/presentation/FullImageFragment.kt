package com.example.findimagesapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.findimagesapp.R
import com.example.findimagesapp.databinding.FragmentFullImageBinding
import com.example.findimagesapp.presentation.viewModels.FullImageViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment with image in original size
 *
 * @author S. Kishkar
 */
@AndroidEntryPoint
class FullImageFragment : Fragment(R.layout.fragment_full_image) {

    private val imageViewModel: FullImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentFullImageBinding.inflate(inflater).apply {

            val circularProgressDrawable = CircularProgressDrawable(context as Context).apply {
                strokeWidth = 5f
                centerRadius = 30f
                start()
            }

            Glide.with(context as Context)
                .load(imageViewModel.getOriginalImageSizeLink(arguments?.getInt(IMAGE_POSITION) as Int))
                .placeholder(circularProgressDrawable)
                .error(imageViewModel.getThumbnailImageSizeLink(arguments?.getInt(IMAGE_POSITION) as Int))
                .into(fullImage)
        }.root

    companion object {
        private const val IMAGE_POSITION = "image_position"

        /**
         * Creates a new instance of a fragment with image in original size
         *
         * @param position position of search result in a search result list
         */
        fun newInstance(position: Int) = FullImageFragment().apply {
            arguments = Bundle().apply { putInt(IMAGE_POSITION, position) }
        }
    }
}