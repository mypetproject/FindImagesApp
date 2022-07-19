package com.example.findimagesapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.SharedElementCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.transition.TransitionInflater
import androidx.transition.TransitionSet
import com.example.findimagesapp.R
import com.example.findimagesapp.databinding.FragmentGridBinding


class GridFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var binding : FragmentGridBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentGridBinding>(
            inflater,
            R.layout.fragment_grid,
            container,
            false
        ).apply {
            imagesRecyclerview.adapter = ImagesListAdapter(
                listOf(
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
            ) { view, position ->
                 imagesListItemClicked(view, position)
            }
            //imagesRecyclerview.layoutManager = GridLayoutManager(this@MainActivity,3)
            imagesRecyclerview.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            recyclerView = imagesRecyclerview
        }

        prepareTransitions()
        postponeEnterTransition()

        return binding.root
    }

    private fun imagesListItemClicked(view : View, position : Int) {
        /*startActivity(
            Intent(context, FullImageActivity::class.java)
                .putExtra(FullImageActivity.EXTRA_INT, position)
        )*/
        MainActivity.currentPosition = position

        //val transitioningView: ImageView = (view as View).findViewById(R.id.imageView)
        //val transitioningView: ImageView = binding.imagesRecyclerview.getChildAt(position) as ImageView
       // val transitioningView = binding.imagesRecyclerview.findViewHolderForAdapterPosition(MainActivity.currentPosition)?.itemView as ImageView

        (this.exitTransition as TransitionSet).excludeTarget(view, true)

        val transitioningView: ImageView = view.findViewById(R.id.imageView)


        this.parentFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true) // Optimize for shared element transition
            //.addSharedElement(transitioningView, position.toString())
            .addSharedElement(transitioningView, transitioningView.transitionName)
            .replace(
                R.id.fragment_container, PagerFragment(), PagerFragment::class.java
                    .simpleName
            )
            .addToBackStack(null)
            .commit()
    }

    private fun prepareTransitions() {
        exitTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.grid_exit_transition)

        // A similar mapping is set at the ImagePagerFragment with a setEnterSharedElementCallback.
        setExitSharedElementCallback(
            object : SharedElementCallback() {
                override fun onMapSharedElements(
                    names: List<String?>,
                    sharedElements: MutableMap<String?, View?>
                ) {
                    // Locate the ViewHolder for the clicked position.
                    val selectedViewHolder: RecyclerView.ViewHolder = recyclerView
                        .findViewHolderForAdapterPosition(MainActivity.currentPosition) ?: return

                    // Map the first shared element name to the child ImageView.
                    sharedElements[names[0]] =
                        selectedViewHolder.itemView.findViewById(R.id.imageView)
                }
            })
    }
}