package com.e.wedding.app.utils

import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.e.wedding.app.base.BaseFragment

@Suppress("UNCHECKED_CAST")
class NonNullMutableLiveData<T>(value: T) : MutableLiveData<T>(value) {
    override fun getValue(): T = super.getValue() as T
    override fun setValue(value: T) = super.setValue(value)
    override fun postValue(value: T) = super.postValue(value)
}

fun ImageView.load(url: String, options: RequestOptions) {
    Glide.with(context)
        .load(url)
        .apply(options)
        .into(this)
}

fun View.getColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(context, colorRes)
}

fun BaseFragment.getColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(requireContext(), colorRes)
}

fun RecyclerView.attachSnapHelper(snapHelper: SnapHelper) {
    snapHelper.attachToRecyclerView(this)
}

fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
    val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}