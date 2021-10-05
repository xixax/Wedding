package com.e.wedding.app.utils

import androidx.lifecycle.MutableLiveData

@Suppress("UNCHECKED_CAST")
class NonNullMutableLiveData<T>(value: T) : MutableLiveData<T>(value) {
    override fun getValue(): T = super.getValue() as T
    override fun setValue(value: T) = super.setValue(value)
    override fun postValue(value: T) = super.postValue(value)
}