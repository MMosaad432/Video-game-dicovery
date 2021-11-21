package com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.thirdwayv.videogamediscovery.R
import com.thirdwayv.videogamediscovery.core.extentions.showSnackBar
import com.thirdwayv.videogamediscovery.core.views.fragments.BaseFragmentWithDefaultObservers
import com.thirdwayv.videogamediscovery.databinding.FragmentFavoriteGenresBinding
import com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.view.adapter.GenresAdapter
import com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.viewmodel.FavoriteGenreResult
import com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.viewmodel.FavoriteGenresActions
import com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.viewmodel.FavoriteGenresViewModel
import com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.viewmodel.FavoriteGenresViewState
import com.thirdwayv.videogamediscovery.features.main_activity.data.model.FABModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FavoriteGenresFragment :
    BaseFragmentWithDefaultObservers<FragmentFavoriteGenresBinding, FavoriteGenresViewState, FavoriteGenresActions, FavoriteGenreResult>(),
    OnFavoriteGenreClicked {

    override val viewModel: FavoriteGenresViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteGenresBinding
        get() = FragmentFavoriteGenresBinding::inflate
    lateinit var genresAdapter: GenresAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel dispatch FavoriteGenresActions.GetGenres
    }

    override fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            state.apply {
                genresAdapter.submitList(genres)
            }
        }
        viewModel.oneTimeViewState.observe(viewLifecycleOwner) { state ->
            if (state.successFavoriteGenreSave) {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.nav_graph, true)
                    .build()
                findNavController().navigate(R.id.GamesFragment, null, navOptions)
            } else {
                showSnackBar("You must select at least one favorite")
            }
        }

    }


    override fun setup() {
        setupFABImage()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        genresAdapter = GenresAdapter(this)
        val gridViewManager = GridLayoutManager(requireContext(), 2)
        binding.genresRv.layoutManager = gridViewManager
        binding.genresRv.adapter = genresAdapter
    }

    private fun setupFABImage() {
        fabModel = FABModel(R.drawable.ic_sharp_thumb_up_alt_24)
    }

    override fun doOnFABClick() {
        saveUserGenres()
    }

    private fun saveUserGenres() {
        viewModel oneTimeDispatch FavoriteGenresActions.SaveUserFavoriteGenres
    }

    override fun onAddFavoriteGenreClicked(genreId: Int) {
        viewModel.internalViewState.favoriteGenresIds.add(genreId)
    }

    override fun onRemoveFavoriteGenreClicked(genreId: Int) {
        viewModel.internalViewState.favoriteGenresIds.remove(genreId)
    }

}