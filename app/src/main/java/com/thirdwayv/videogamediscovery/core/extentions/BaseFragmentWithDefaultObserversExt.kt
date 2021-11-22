package com.thirdwayv.videogamediscovery.core.extentions

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.thirdwayv.videogamediscovery.core.views.fragments.BaseFragmentWithDefaultObservers
import com.thirdwayv.videogamediscovery.core.utils.SNACK_BAR_DURATION

fun BaseFragmentWithDefaultObservers<*, *, *, *>.showSnackBar(
    string: String, duration: Int = SNACK_BAR_DURATION,
    action: Pair<String, () -> Unit>? = null
) {
    val view: View = this.requireView()


    val snackBar = Snackbar.make(
        view,
        string,
        duration
    )
    action?.let {
        snackBar.setAction(action.first) {
            action.second.invoke()
        }
    }
    snackBar.show()
}