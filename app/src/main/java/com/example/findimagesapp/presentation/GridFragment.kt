package com.example.findimagesapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findimagesapp.R
import com.example.findimagesapp.databinding.FragmentGridBinding
import com.example.findimagesapp.presentation.viewModels.GridViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GridFragment : Fragment() {

    private var binding: FragmentGridBinding? = null
    private val model: GridViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (binding == null) binding = DataBindingUtil.inflate<FragmentGridBinding>(
            inflater,
            R.layout.fragment_grid,
            container,
            false
        ).apply {
            imagesRecyclerview.adapter = ImagesListAdapter(
                listOf()
            ) { position ->
                imagesListItemClicked(position)
            }
            imagesRecyclerview.layoutManager =
                GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)

            imagesRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if ((imagesRecyclerview.layoutManager as GridLayoutManager).findLastVisibleItemPosition() == (imagesRecyclerview.adapter as ImagesListAdapter).itemCount - 1) downloadNewData()
                }
            })
        }
        initObservers()
        return binding?.root as View
    }

    private fun initObservers() =
        lifecycleScope.launch {
            model.imagesListFlow.collect { images ->
                (binding?.imagesRecyclerview?.adapter as ImagesListAdapter).run {
                    setImageResultList(images.map { it.thumbnailLink })
                    notifyDataSetChanged()
                }
            }
        }


    private fun downloadNewData() {
        model.downloadAdditionalData()
    }

    override fun onResume() {
        if (MainActivity.currentPosition >= 0) {
            binding?.imagesRecyclerview?.scrollToPosition(MainActivity.currentPosition)
            MainActivity.currentPosition = -1
        }
        initObservers()
        super.onResume()
    }

    private fun imagesListItemClicked(position: Int) {
        MainActivity.currentPosition = position
        (activity as MainActivity).startPagerFragment()
    }
}