package com.thirdwayv.videogamediscovery.core.views.adapters

import androidx.paging.DataSource
import com.thirdwayv.videogamediscovery.core.views.viewmodel.BaseViewModel

abstract class BaseDataFactory<T>(private val baseViewModel: BaseViewModel<*,*,*>) : DataSource.Factory<Long, T>() {

    abstract fun onCreateDataSource(baseViewModel: BaseViewModel<*,*,*>): BaseDataSource<T>

    override fun create(): DataSource<Long,T> {
        return  onCreateDataSource(baseViewModel)
    }
}