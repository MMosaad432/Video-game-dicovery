package com.thirdwayv.videogamediscovery.core.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.thirdwayv.videogamediscovery.core.views.viewholder.BaseViewHolder
import com.thirdwayv.videogamediscovery.databinding.IbaseItemLoadingBinding

abstract class BasePagingAdapter<DataItem, VH : BaseViewHolder<DataItem, *>>
protected constructor(callback: DiffUtil.ItemCallback<DataItem>) :
    PagedListAdapter<DataItem, BaseViewHolder<DataItem, *>>(callback), OnViewStatusChange {

    private var hasExtraItems: Boolean = false
    private val TYPE_PROGRESS = 0
    private val TYPE_ITEM = 1

    abstract fun getViewHolder(viewGroup: ViewGroup): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DataItem, *> {
        return when (viewType) {
            TYPE_PROGRESS -> {
                val binding = IbaseItemLoadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                LoadingViewHolder<IbaseItemLoadingBinding>(binding)
            }
            else -> getViewHolder(parent)
        }

    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<DataItem, *>,
        position: Int
    ) {
        if (position <= currentList?.size?.minus(1) ?: 0)
            getItem(position)?.let {
                holder.onBind(it)
            }
    }

    override fun getItemCount(): Int {
        return if (hasExtraItems) super.getItemCount() + 1
        else super.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraItems && position == itemCount - 1) {
            TYPE_PROGRESS
        } else {
            TYPE_ITEM
        }
    }

    override fun showLoading(b: Boolean) {
        hasExtraItems = b
        if (b)
            notifyItemInserted(itemCount)
        else
            notifyItemChanged(itemCount)
    }

    inner class LoadingViewHolder<vb : ViewBinding>(mBinding: vb) :
        BaseViewHolder<DataItem, vb>(mBinding) {
        override fun onBind(item: DataItem) {}
    }

}