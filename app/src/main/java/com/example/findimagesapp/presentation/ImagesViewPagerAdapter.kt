package com.example.findimagesapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ImagesViewPagerAdapter (fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity){

    var urls : List<String> = emptyList()

    override fun getItemCount(): Int = urls.size

    override fun createFragment(position: Int): Fragment {
        return FullImageFragment.newInstance(position)
    }
}