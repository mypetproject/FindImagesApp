package com.example.findimagesapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import androidx.viewpager2.widget.ViewPager2
import com.example.findimagesapp.R
import com.example.findimagesapp.databinding.FragmentPagerBinding


class PagerFragment : Fragment() {

    private lateinit var viewPager : ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewPager = inflater.inflate(R.layout.fragment_pager, container,false) as ViewPager2
        //viewPager.adapter = ImagesViewPagerAdapter(activity as FragmentActivity)

        DataBindingUtil.setContentView<FragmentPagerBinding>(activity as MainActivity, R.layout.fragment_pager).apply {

            viewPager = fullImagesViewpager

            fullImagesViewpager.adapter = ImagesViewPagerAdapter(activity as MainActivity).also {
                it.urls = FullImageFragment.list
            }
        }
       // viewPager.currentItem = MainActivity.currentPosition

       viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
           override fun onPageSelected(position: Int) {
               MainActivity.currentPosition = position
               super.onPageSelected(position)
           }
       })

        prepareSharedElementTransition()
        postponeEnterTransition()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun prepareSharedElementTransition() {
        val transition: Transition = TransitionInflater.from(context)
            .inflateTransition(R.transition.image_shared_element_transition)
        sharedElementEnterTransition = transition

        setEnterSharedElementCallback(
            object : SharedElementCallback() {
                override fun onMapSharedElements(
                    names: List<String?>,
                    sharedElements: MutableMap<String?, View?>
                ) {
                    // Locate the image view at the primary fragment (the ImageFragment that is currently
                    // visible). To locate the fragment, call instantiateItem with the selection position.
                    // At this stage, the method will simply return the fragment at the position and will
                    // not create a new one.
                    /*val currentFragment = viewPager.adapter
                        .instantiateItem(viewPager, MainActivity.currentPosition) as Fragment
                 */
                    viewPager.getChildAt(MainActivity.currentPosition) ?: return
                   // val view = currentFragment.view ?: return
                    // Map the first shared element name to the child ImageView.
                    //sharedElements[names[0]] = view.findViewById(R.id.imageView)
                    sharedElements[names[0]] = viewPager.getChildAt(MainActivity.currentPosition).findViewById(R.id.full_image)
                }
            })
    }
}