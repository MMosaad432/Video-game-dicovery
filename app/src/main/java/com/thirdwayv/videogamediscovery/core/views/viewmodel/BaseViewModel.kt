package com.thirdwayv.videogamediscovery.core.views.viewmodel

import androidx.lifecycle.*
import com.thirdwayv.videogamediscovery.core.extentions.toOneTimeObserve
import com.thirdwayv.videogamediscovery.core.others.CoroutineContextProvider
import com.thirdwayv.videogamediscovery.core.others.SingleLiveEvent

/**
 * Abstract class used to define Base View model
 * [ViewState] represents the state of the view returned from [reduce]
 * [Action] represents the action of the view to be handled by [handle]
 * [Result] results returned from Use cases to be reduced in viewMode class using [reduce]
 */
abstract class BaseViewModel<S : ViewState, A : Action, R : Result>(val dispatcher: CoroutineContextProvider) : ViewModel() {

    val iTriggerViews  = object : ITriggerViews{
        override fun showLoading() {
            mShowFullScreenLoader.value = true
        }

        override fun hideLoading() {
            mShowFullScreenLoader.value = false
        }

        override fun showSnackBarMessage(message: String) {
            mSnackBarMessage.value = message
        }
    }
    /**
     * loaderLiveData [MutableLiveData]<[Boolean]> used to toggle progress loader visibility
     */
    val mShowFullScreenLoader = MutableLiveData<Boolean>()

    /**
     * loaderLiveData [MutableLiveData]<[Message]> used to show toast in fragment when needed
     */
    val mSnackBarMessage = MutableLiveData<String>()

    /**
     * internalViewState instance [ViewState] holds default/initial state of the view
     */
    open val internalViewState: S? = null

    /**
     * nextAction [MutableLiveData]<[Action]> used to observe if any action is called
     */
    private val nextAction = MutableLiveData<A>()

    /**
     * oneTimeAction [MutableLiveData]<[Action]> used to observe if any action is called
     */
    private val oneTimeAction = SingleLiveEvent<A>()

    /**
     * Abstract function need to be implemented in order to handle actions from the view
     *
     * @param action the action taken by the user or the system to be handled
     * @return [LiveData]<[Result]> the result of the handled action
     */
    abstract fun handle(action: A): LiveData<R>

    /**
     * Abstract function need to be implemented in order to reduce [Result] to [ViewState]
     *
     * @param result the result returned from handle function
     * @return [ViewState] the view state reduced according to the result
     */
    abstract fun reduce(result: R): S

    /**
     * viewState [LiveData]<[ViewState]>
     */
    val viewState: LiveData<S> = nextAction
        .switchMap {
            handle(it)
        }.map {
            reduce(it)
        }.distinctUntilChanged()

    /**
     * oneTimeViewState [LiveData]<[ViewState]>
     */
    val oneTimeViewState: LiveData<S> = oneTimeAction
        .switchMap {
            handle(it)
        }.map {
            reduce(it)
        }.distinctUntilChanged().toOneTimeObserve()

    /**
     * Function used to be called from the view to dispatch action to viewModel to be handled
     * infix function so you can call it ex: "viewModel dispatch action"
     * @param action [Action] used to set value of [nextAction] to be handled using [viewState] implementation
     */
    infix fun dispatch(action: A) {
        nextAction.value = action
    }

    /**
     * Function used to be called from the view to oneTimeDispatch action to viewModel to be handled
     * infix function so you can call it ex: "viewModel oneTimeDispatch action"
     * @param action [Action] used to set value of [oneTimeAction] to be handled using [oneTimeViewState] implementation
     */
    infix fun oneTimeDispatch(action: A) {
        oneTimeAction.value = action
    }
}