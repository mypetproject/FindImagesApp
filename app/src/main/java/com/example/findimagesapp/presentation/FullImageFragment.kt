package com.example.findimagesapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.findimagesapp.R
import com.example.findimagesapp.databinding.FragmentFullImageBinding

class FullImageFragment : Fragment(R.layout.fragment_full_image){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentFullImageBinding.inflate(inflater).apply{
            Glide.with(context as Context).load(list[arguments?.getInt(IMAGE_POSITION) as Int]).into(fullImage)
            parentFragment?.startPostponedEnterTransition()
        }.root

    companion object {
        const val IMAGE_POSITION = "note_id"

        fun newInstance(position: Int) = FullImageFragment().apply {
            arguments = Bundle().apply { putInt(IMAGE_POSITION, MainActivity.currentPosition) }
        }

        val list = listOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMzLN9iUjPiaNdA8w7NtzSTuJI0UPbDFOQKh3IXF6buUYmkaNr",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYfgh6R22EUz-8EUwh-e68sSEFOHWXeZHuysRW_DPkhahMgdwJ",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrdINKE0Mf9NtmvZqt8GWSwDXjvtFGrRyy1SsO9l-zFyFLoMWg",
            "https://serpapi.com/searches/62d4f5841baebb2c4f3d572d/images/05109f1d08e78809ac8ac33e43ac30be36fe799ed67b01bb8b42b1203f73ed6e.png",
            "https://serpapi.com/searches/62d4f5841baebb2c4f3d572d/images/05109f1d08e7880997a7d55a45910361fe68c6ecad37992ee517563067e91c80.png",
            "https://serpapi.com/searches/62d4f5841baebb2c4f3d572d/images/05109f1d08e78809a9adf0b791c1b11ad081c99392bb2fdb57a96c9169d40475.png",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsksRtwy52-DaumqE5-LLXL_zLg4bcDQneJvNv8OOFKFWnEDSjnfex8r3SZeUbW8pxgv4&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMzLN9iUjPiaNdA8w7NtzSTuJI0UPbDFOQKh3IXF6buUYmkaNr",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYfgh6R22EUz-8EUwh-e68sSEFOHWXeZHuysRW_DPkhahMgdwJ",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrdINKE0Mf9NtmvZqt8GWSwDXjvtFGrRyy1SsO9l-zFyFLoMWg",
            "https://serpapi.com/searches/62d4f5841baebb2c4f3d572d/images/05109f1d08e78809ac8ac33e43ac30be36fe799ed67b01bb8b42b1203f73ed6e.png",
            "https://serpapi.com/searches/62d4f5841baebb2c4f3d572d/images/05109f1d08e7880997a7d55a45910361fe68c6ecad37992ee517563067e91c80.png",
            "https://serpapi.com/searches/62d4f5841baebb2c4f3d572d/images/05109f1d08e78809a9adf0b791c1b11ad081c99392bb2fdb57a96c9169d40475.png",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsksRtwy52-DaumqE5-LLXL_zLg4bcDQneJvNv8OOFKFWnEDSjnfex8r3SZeUbW8pxgv4&usqp=CAU",
            "https://serpapi.com/searches/62d4f5841baebb2c4f3d572d/images/05109f1d08e788090ac26d34e23713b4c040f3ebc802ff3ac04d261e7c9f0f67.jpeg",
            "https://serpapi.com/searches/62d4f5841baebb2c4f3d572d/images/05109f1d08e78809f56a82b71793c756c9818aa5ac098437c8412bf1c526017c.jpeg",
            "https://serpapi.com/searches/62d4f5841baebb2c4f3d572d/images/05109f1d08e78809f463636f8f6a1186b7f44d6182a513232b9886057b1d5c01.jpeg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcA1iF-9eEhTCKGYvRf4ZYndRPrARPDRj50VLUxArhjM7M8XMcdf4sfDCtQTr3Ebf19So&usqp=CAU",
            "https://serpapi.com/searches/62d4f5841baebb2c4f3d572d/images/05109f1d08e788090ac26d34e23713b4c040f3ebc802ff3ac04d261e7c9f0f67.jpeg",
            "https://serpapi.com/searches/62d4f5841baebb2c4f3d572d/images/05109f1d08e78809f56a82b71793c756c9818aa5ac098437c8412bf1c526017c.jpeg",
            "https://serpapi.com/searches/62d4f5841baebb2c4f3d572d/images/05109f1d08e78809f463636f8f6a1186b7f44d6182a513232b9886057b1d5c01.jpeg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcA1iF-9eEhTCKGYvRf4ZYndRPrARPDRj50VLUxArhjM7M8XMcdf4sfDCtQTr3Ebf19So&usqp=CAU"
        )
    }
}