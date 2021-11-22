package com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.views.adapter

import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.thirdwayv.videogamediscovery.core.views.adapters.BasePagingAdapter
import com.thirdwayv.videogamediscovery.core.views.viewholder.BaseViewHolder
import com.thirdwayv.videogamediscovery.databinding.ItemGameBinding
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.data.model.Game
import com.thirdwayv.videogamediscovery.features.favorite_geners_video_games.presentation.views.fragment.OnGameClickedListener
import dagger.hilt.android.internal.managers.ViewComponentManager

class GamesAdapter(
    private val mListener: OnGameClickedListener
) : BasePagingAdapter<Game, GamesAdapter.GamesViewHolder>(callback) {


    inner class GamesViewHolder(private val binding: ItemGameBinding) :
        BaseViewHolder<Game, ItemGameBinding>(binding) {
        init {
            setItemWidth()
            addRootClickListener()
        }

        private fun addRootClickListener() {
            binding.root.setOnClickListener {
                val item = getItem(adapterPosition)
                item?.let {
                    mListener.onGameClicked(it)
                }
            }
        }

        private fun setItemWidth() {
            val rootLayoutParam = binding.root.layoutParams
            val metrics = getDisplayMetrics()
            rootLayoutParam.width = metrics.widthPixels / 2
            binding.root.layoutParams = rootLayoutParam
        }

        private fun getDisplayMetrics(): DisplayMetrics {
            val outMetrics = DisplayMetrics()
            val activity =
                (binding.root.context as ViewComponentManager.FragmentContextWrapper).baseContext as AppCompatActivity

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val display = activity.display
                display?.getRealMetrics(outMetrics)
            } else {
                @Suppress("DEPRECATION")
                val display = activity.windowManager.defaultDisplay
                @Suppress("DEPRECATION")
                display.getMetrics(outMetrics)
            }
            return outMetrics
        }

        override fun onBind(game: Game) {
            Glide.with(binding.root.context).load(game.backgroundImage).into(binding.gameImage)
            binding.gameName.text = game.name
            binding.gameRate.text = "${game.rating}/5"
        }
    }

    companion object {
        internal var callback: DiffUtil.ItemCallback<Game> =
            object : DiffUtil.ItemCallback<Game>() {
                override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                    return false
                }

                override fun areContentsTheSame(
                    oldItem: Game,
                    newItem: Game
                ): Boolean {
                    return false
                }

            }
    }

    override fun getViewHolder(viewGroup: ViewGroup): GamesViewHolder {
        val binding =
            ItemGameBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return GamesViewHolder(binding)
    }
}