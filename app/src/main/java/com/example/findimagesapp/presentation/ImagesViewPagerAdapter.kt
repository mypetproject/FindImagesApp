package com.example.findimagesapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Adapter for image list in original size
 *
 * @author S. Kishkar
 */
class ImagesViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    var itemsCount = 0

    override fun getItemCount(): Int = itemsCount

    override fun createFragment(position: Int): Fragment {
        return FullImageFragment.newInstance(position)
    }
}