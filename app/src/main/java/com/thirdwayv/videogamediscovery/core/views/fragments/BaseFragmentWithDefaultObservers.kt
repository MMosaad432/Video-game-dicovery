package com.thirdwayv.videogamediscovery.core.views.fragments

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.thirdwayv.videogamediscovery.core.extentions.showSnackBar
import com.thirdwayv.videogamediscovery.core.views.viewmodel.Action
import com.thirdwayv.videogamediscovery.core.views.viewmodel.Result
import com.thirdwayv.videogamediscovery.core.views.viewmodel.ViewState
import com.thirdwayv.videogamediscovery.features.main_activity.presentation.views.LoaderTrigger

abstract class BaseFragmentWithDefaultObservers<VB : ViewBinding, S : ViewState, A : Action, R : Result> :
    BaseFragment<VB, S, A, R>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDefaultObservers()
        setupObservers()
    }

    private fun setupDefaultObservers() {
        viewModel.mShowFullScreenLoader.observe(viewLifecycleOwner) {
            val activity = requireActivity()
            if (activity is LoaderTrigger) {
                activity.toggleLoader(it)
            }
        }

        viewModel.mSnackBarMessage.observe(viewLifecycleOwner) {
            showSnackBar(it)

        }
    }

    abstract fun setupObservers()
}