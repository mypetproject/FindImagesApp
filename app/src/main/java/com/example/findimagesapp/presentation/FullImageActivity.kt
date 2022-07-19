package com.example.findimagesapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.findimagesapp.R
import com.example.findimagesapp.databinding.ActivityFullImageBinding

class FullImageActivity : AppCompatActivity() {

    private val adapter by lazy { ImagesViewPagerAdapter(this) }

    private lateinit var binding : ActivityFullImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityFullImageBinding>(this, R.layout.activity_full_image).apply {
            fullImagesViewpager.adapter = adapter.also {
                it.urls = FullImageFragment.list
            }
        }
        setPagerCurrentItem()

    }

    private fun setPagerCurrentItem() {
        binding.fullImagesViewpager.setCurrentItem(
            intent.getIntExtra(EXTRA_INT, 0), false
        )
        //setCurrentPagerPosition = false
    }

    companion object {
        const val EXTRA_INT = "extra_long"
    }
}