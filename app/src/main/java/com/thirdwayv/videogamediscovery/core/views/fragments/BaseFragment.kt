package com.thirdwayv.videogamediscovery.core.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.thirdwayv.videogamediscovery.core.views.viewmodel.ViewState
import com.thirdwayv.videogamediscovery.core.views.viewmodel.Action
import com.thirdwayv.videogamediscovery.core.views.viewmodel.BaseViewModel
import com.thirdwayv.videogamediscovery.core.views.viewmodel.Result


abstract class BaseFragment<VB : ViewBinding, S : ViewState, A : Action, R : Result> : Fragment() {

    abstract val viewModel: BaseViewModel<S, A, R>
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    abstract fun setup()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}