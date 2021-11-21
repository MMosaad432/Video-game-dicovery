package com.thirdwayv.videogamediscovery.features.main_activity.domain.usecase

import com.thirdwayv.videogamediscovery.core.domain.SuspendableUseCase
import com.thirdwayv.videogamediscovery.core.others.CoroutineContextProvider
import com.thirdwayv.videogamediscovery.core.views.viewmodel.ITriggerViews
import com.thirdwayv.videogamediscovery.features.main_activity.domain.repository.IMainRepo
import com.thirdwayv.videogamediscovery.features.main_activity.presentation.viewmodel.MainResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetIsFavoriteSelectedUseCase @Inject constructor(
    private val iMainRepo: IMainRepo,
    dispatcher: CoroutineContextProvider
) : SuspendableUseCase<Unit, MainResult>(
    dispatcher
) {
    override suspend fun execute(
        input: Unit?,
        iTriggerViews: ITriggerViews?
    ): MainResult {
        return MainResult.SuccessFetch(iMainRepo.getIsFavoriteSelected())
    }

}