package com.thirdwayv.videogamediscovery.core.extentions

import android.R
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat


fun View.addRippleEffect() = with(TypedValue()) {
    context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
    foreground = ContextCompat.getDrawable(context, resourceId)
}