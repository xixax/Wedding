package com.e.wedding.app.view.slideshow

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.activityViewModel
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.model.Image
import com.e.wedding.app.utils.attachSnapHelper
import com.e.wedding.app.view.gallery.ImageViewModel
import com.e.wedding.databinding.FragmentSlideshowBinding

class SlideShowFragment : BaseFragment(R.layout.fragment_slideshow) {
    private val binding by viewBinding(FragmentSlideshowBinding::bind)
    private val viewModel by activityViewModel<ImageViewModel>()

    private val adapter = SlideShowAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupObservers()
    }

    private fun setupRecycler() = with(binding) {
        rvSlideshow.adapter = adapter
        rvSlideshow.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvSlideshow.attachSnapHelper(PagerSnapHelper())
    }

    private fun setupObservers() = with(viewModel) {
        images.observe(viewLifecycleOwner) { updateImages(it) }
        imageSelected.observe(viewLifecycleOwner) { updateImageSelected(it) }
    }

    private fun updateImageSelected(image: Image?) = with(binding) {
        if (image == null) return@with
        val position = adapter.getItemPosition(image) ?: return@with
        logger.d(TAG, "updateImageSelected", "position=$position")
        rvSlideshow.scrollToPosition(position)
    }

    private fun updateImages(images: List<Image>) {
        logger.d(TAG, "updateImages")
        adapter.updateImages(images)
    }
}