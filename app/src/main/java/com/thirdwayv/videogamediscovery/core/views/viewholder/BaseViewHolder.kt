package com.thirdwayv.videogamediscovery.core.views.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.thirdwayv.videogamediscovery.core.extentions.addRippleEffect

abstract class BaseViewHolder<DataItem, vb : ViewBinding>(mBinding: vb) :
    RecyclerView.ViewHolder(mBinding.root) {

    init {
        //add ripple effect to the item
        super.itemView.isClickable = true
        super.itemView.addRippleEffect()
    }

    abstract fun onBind(item: DataItem)

    open fun onBind(item: DataItem, payload: MutableList<Any>) {
        onBind(item)
    }
}