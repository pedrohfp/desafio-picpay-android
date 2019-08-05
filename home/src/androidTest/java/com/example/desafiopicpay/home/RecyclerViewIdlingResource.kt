package com.example.desafiopicpay.home

import android.view.View
import android.view.ViewTreeObserver
import androidx.test.espresso.IdlingResource
import kotlinx.android.synthetic.main.activity_home.userListRecyclerView

inline fun <T: View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

internal class RecyclerViewIdlingResource(private val activity: HomeActivity): IdlingResource {

    private var isIdle: Boolean = false
    private lateinit var resourceCallback: IdlingResource.ResourceCallback


    override fun getName(): String =
        RecyclerViewIdlingResource::class.java.name


    override fun isIdleNow(): Boolean {
        if (isIdle) return true

        isIdle()

        return isIdle
    }

    private fun isIdle() {
        activity.userListRecyclerView.afterMeasured {
            isIdle = true
            resourceCallback.onTransitionToIdle()
        }
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        resourceCallback = callback
    }
}