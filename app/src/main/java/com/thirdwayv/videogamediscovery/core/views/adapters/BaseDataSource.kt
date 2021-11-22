package com.thirdwayv.videogamediscovery.core.views.adapters

import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import com.thirdwayv.videogamediscovery.core.views.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseDataSource<T>(val baseViewModel: BaseViewModel<*,*,*>) : PageKeyedDataSource<Long, T>() {

    abstract suspend fun fetchData(pageNumber: Int,pageSize:Int): List<T>

    override fun  loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, T>) {

        baseViewModel.viewModelScope.launch(Dispatchers.Main) {
            val result = fetchData(1,params.requestedLoadSize)
            callback.onResult(result, null, 2)
        }
    }


    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, T>) {

    }


    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, T>) {

        baseViewModel.viewModelScope.launch(Dispatchers.Main) {
            val nextKey = (params.key)
            val result = fetchData(nextKey.toInt(),params.requestedLoadSize)
            callback.onResult(result, nextKey + 1)
        }

    }
}