package com.e.wedding.app.view.gallery

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.activityViewModel
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.model.Image
import com.e.wedding.databinding.FragmentGalleryBinding

const val NUMBER_OF_COLUMNS = 3

class GalleryFragment : BaseFragment(R.layout.fragment_gallery) {
    private val binding by viewBinding(FragmentGalleryBinding::bind)
    private val viewModel by activityViewModel<ImageViewModel>()

    private val imageClick: (image: Image) -> Unit = { viewModel.imageClick(it) }
    private val gridAdapter = GalleryAdapter(imageClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
        viewModel.getImages()
    }

    private fun setupUI() = with(binding.rvGallery) {
        adapter = gridAdapter
        layoutManager = GridLayoutManager(requireContext(), NUMBER_OF_COLUMNS)
    }

    private fun setupObservers() = with(viewModel) {
        images.observe(viewLifecycleOwner) { updateImages(it) }
    }

    private fun updateImages(images: List<Image>) {
        logger.d(TAG, "updateImages")
        gridAdapter.updateImages(images)
    }
}