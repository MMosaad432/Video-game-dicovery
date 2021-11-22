package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.views.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.thirdwayv.videogamediscovery.R
import com.thirdwayv.videogamediscovery.core.extentions.showSnackBar
import com.thirdwayv.videogamediscovery.core.views.fragments.BaseFragmentWithDefaultObservers
import com.thirdwayv.videogamediscovery.databinding.FragmentGamesBinding
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.Game
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.viewmodel.GamesActions
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.viewmodel.GamesResult
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.viewmodel.GamesViewModel
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.viewmodel.GamesViewState
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.views.adapter.GamesAdapter
import com.thirdwayv.videogamediscovery.features.main_activity.data.model.FABModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GamesFragment :
    BaseFragmentWithDefaultObservers<FragmentGamesBinding, GamesViewState, GamesActions, GamesResult>(),
    OnGameClickedListener {
    override val viewModel: GamesViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGamesBinding
        get() = FragmentGamesBinding::inflate
    private lateinit var gamesAdapter: GamesAdapter

    override fun setup() {
        fetchGames()
        setupFABImage()
        initRecyclerView()
    }

    private fun fetchGames() {
        viewModel.handlePaging()
    }

    private fun initRecyclerView() {
        gamesAdapter = GamesAdapter(this)
        val gridViewManager = GridLayoutManager(requireContext(), 2)
        binding.gamesRv.layoutManager = gridViewManager
        binding.gamesRv.adapter = gamesAdapter
    }

    private fun setupFABImage() {
        fabModel = FABModel(R.drawable.ic_sharp_star_border)
    }

    override fun setupObservers() {
        viewModel.gamesLiveData?.observe(viewLifecycleOwner) {
            gamesAdapter.submitList(it)

        }
    }

    override fun doOnFABClick() {
        navigateToFavorite()
    }

    private fun navigateToFavorite() {
        findNavController().navigate(R.id.action_GamesFragment_to_FavoriteGenresFragment)
    }

    override fun onGameClicked(game: Game) {
        showSnackBar(game.name)
    }
}