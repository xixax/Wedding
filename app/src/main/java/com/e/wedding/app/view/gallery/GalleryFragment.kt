package com.e.wedding.app.view.gallery

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.base.viewModel
import com.e.wedding.app.model.Image
import com.e.wedding.databinding.FragmentGalleryBinding

const val NUMBER_OF_COLUMNS = 3

class GalleryFragment : BaseFragment(R.layout.fragment_gallery) {
    private val binding by viewBinding(FragmentGalleryBinding::bind)
    private val viewModel by viewModel<GalleryViewModel>()

    private val imageClick: (image: Image) -> Unit = { viewModel.imageClick(it) }
    private val imageLongClick: (image: Image) -> Unit = { viewModel.imageLongClick(it) }
    private lateinit var adapter: GalleryAdapter
    private val snapHelper = PagerSnapHelper()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
    }

    private fun setupUI() = with(binding) {
        adapter = GalleryAdapter(requireContext(), imageClick, imageLongClick)
        rvGallery.adapter = adapter
        updateMode(viewModel.mode.value ?: GalleryMode.Gallery)
    }

    private fun setupObservers() = with(viewModel) {
        images.observe(viewLifecycleOwner) { updateImages(it) }
        mode.observe(viewLifecycleOwner) { updateMode(it) }
    }

    private fun updateImages(images: List<Image>) {
        logger.d(TAG, "updateImages")
        adapter.updateImages(images)
    }

    private fun updateMode(mode: GalleryMode) = with(binding) {
        adapter.updateMode(mode)
        when (mode) {
            GalleryMode.SlideShow -> {
                snapHelper.attachToRecyclerView(rvGallery)
                rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
            GalleryMode.Gallery -> {
                snapHelper.attachToRecyclerView(null)
                rvGallery.layoutManager = GridLayoutManager(requireContext(), NUMBER_OF_COLUMNS)
            }
        }

    }
}