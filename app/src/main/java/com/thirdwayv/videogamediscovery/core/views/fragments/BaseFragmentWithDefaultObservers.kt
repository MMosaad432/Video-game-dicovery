package com.thirdwayv.videogamediscovery.core.views.fragments

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.thirdwayv.videogamediscovery.core.views.viewmodel.ViewState
import com.thirdwayv.videogamediscovery.core.views.viewmodel.Action
import com.thirdwayv.videogamediscovery.core.views.viewmodel.Result

abstract class BaseFragmentWithDefaultObservers<VB : ViewBinding, S : ViewState, A : Action, R : Result> :
    BaseFragment<VB, S, A, R>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDefaultObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupDefaultObservers() {
        viewModel.mShowFullScreenLoader.observe(viewLifecycleOwner) {

        }

        viewModel.mSnackBarMessage.observe(viewLifecycleOwner) {

        }
    }

    abstract fun setupObservers()
}