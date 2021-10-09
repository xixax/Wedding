package com.e.wedding.app.view.slideshow

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.request.RequestOptions
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.activityViewModel
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.model.Image
import com.e.wedding.app.utils.getColor
import com.e.wedding.app.utils.load
import com.e.wedding.app.view.gallery.ImageViewModel
import com.e.wedding.databinding.FragmentSlideshowBinding

class SlideShowFragment : BaseFragment(R.layout.fragment_slideshow) {
    private val binding by viewBinding(FragmentSlideshowBinding::bind)
    private val viewModel by activityViewModel<ImageViewModel>()

    private val slideAdapter = SlideShowAdapter()
    private val snapHelper = PagerSnapHelper()
    private val options by lazy { RequestOptions().error(ColorDrawable(getColor(R.color.colorPrimary))) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSlideShow()
        setupObservers()
    }

    private fun setupSlideShow() = with(binding.rvSlideshow) {
        adapter = slideAdapter
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        snapHelper.attachToRecyclerView(this)
    }

    private fun setupObservers() = with(viewModel) {
        images.observe(viewLifecycleOwner) { updateImages(it) }
        imageSelected.observe(viewLifecycleOwner) { updateImageSelected(it) }
    }

    private fun updateImageSelected(image: Image?) = with(binding) {
        logger.d(TAG, "updateImageSelected")
        image?.let { ivImage.load(image.url, options) }
    }

    private fun updateImages(images: List<Image>) {
        logger.d(TAG, "updateImages")
        slideAdapter.updateImages(images)
    }
}