package com.thirdwayv.videogamediscovery.features.main_activity.presentation.viewmodel

import androidx.lifecycle.liveData
import com.thirdwayv.videogamediscovery.core.others.CoroutineContextProvider
import com.thirdwayv.videogamediscovery.core.views.viewmodel.BaseViewModel
import com.thirdwayv.videogamediscovery.features.main_activity.domain.usecase.GetIsFavoriteSelectedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getIsFavoriteSelectedUseCase: GetIsFavoriteSelectedUseCase,
    dispatcher: CoroutineContextProvider
) :
    BaseViewModel<MainViewState, MainActions, MainResult>(dispatcher) {
    override fun handle(action: MainActions) = liveData {
        if (action is MainActions.GetIsFavoriteSelected) {
            emit(getIsFavoriteSelectedUseCase.execute())
        }
    }

    override fun reduce(result: MainResult): MainViewState = when (result) {
        is MainResult.SuccessFetch -> MainViewState.SuccessFetch(result.isFavoriteSelected)
    }

}