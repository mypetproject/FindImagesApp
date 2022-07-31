package com.example.findimagesapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.findimagesapp.R
import com.example.findimagesapp.databinding.FragmentPagerBinding
import com.example.findimagesapp.presentation.viewModels.PagerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Fragment with ViewPager for full size images
 *
 * @author S. Kishkar
 */
@AndroidEntryPoint
class PagerFragment : Fragment() {

    private lateinit var binding: FragmentPagerBinding
    private val model: PagerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        DataBindingUtil.inflate<FragmentPagerBinding>(
            inflater,
            R.layout.fragment_pager,
            container,
            false
        ).apply {
            binding = this

            fullImagesViewpager.run {
                adapter = ImagesViewPagerAdapter(activity as MainActivity).apply {
                    itemsCount = model.imagesListFlow.value.size
                }

                offscreenPageLimit = 3
                setCurrentItem(MainActivity.currentPosition, false)

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        MainActivity.currentPosition = position
                    }
                })
            }

            openLinkButton.setOnClickListener {
                startActivity(Intent(context, WebViewActivity::class.java).apply {
                    putExtra(
                        WebViewActivity.URL,
                        model.getCurrentUrl(fullImagesViewpager.currentItem)
                    )
                })
            }
            (activity as MainActivity).fullImageShown()
            initObservers()
        }.root

    private fun initObservers() =
        lifecycleScope.launch {
            model.imagesListFlow.collect {
                (binding.fullImagesViewpager.adapter as ImagesViewPagerAdapter).itemsCount = it.size
            }
        }

    override fun onDestroy() {
        (activity as MainActivity).attachGridFragment()
        super.onDestroy()
    }
}