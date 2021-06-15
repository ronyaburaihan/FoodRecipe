package com.techdoctorbd.foodrecipe.utils

import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import coil.load
import com.techdoctorbd.foodrecipe.R

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T) {
            removeObserver(this)
            observer.onChanged(t)
        }
    })
}

fun ImageView.loadImageFromUrl(url: String) = this.load(url) {
    crossfade(true)
    crossfade(600)
    error(R.drawable.ic_error_placeholder)
}