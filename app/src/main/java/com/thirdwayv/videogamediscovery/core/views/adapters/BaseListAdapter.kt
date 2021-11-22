package com.thirdwayv.videogamediscovery.core.views.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.thirdwayv.videogamediscovery.core.views.viewholder.BaseViewHolder

abstract class BaseListAdapter<T, vb : ViewBinding, VH : BaseViewHolder<T, vb>>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(diffCallback) {


    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        holder.onBind(getItem(position), payloads)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }

}