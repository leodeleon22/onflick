package com.leodeleon.onflick.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun<reified T> LifecycleOwner.observe(liveData: LiveData<T>, crossinline onValue: (T) -> Unit) {
    liveData.observe(this, Observer {
        onValue(it)
    })
}