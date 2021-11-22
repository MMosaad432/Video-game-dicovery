package com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.view.adapter

import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.thirdwayv.videogamediscovery.R
import com.thirdwayv.videogamediscovery.core.views.adapters.BaseListAdapter
import com.thirdwayv.videogamediscovery.core.views.viewholder.BaseViewHolder
import com.thirdwayv.videogamediscovery.databinding.ItemGenreBinding
import com.thirdwayv.videogamediscovery.features.favorite_generes.data.model.Genre
import com.thirdwayv.videogamediscovery.features.favorite_generes.presentation.view.fragment.OnFavoriteGenreClicked
import dagger.hilt.android.internal.managers.ViewComponentManager

class GenresAdapter(
    private val mListener: OnFavoriteGenreClicked
) : BaseListAdapter<Genre, ItemGenreBinding, GenresAdapter.GenresViewHolder>(callback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenresViewHolder(binding)
    }

    inner class GenresViewHolder(private val binding: ItemGenreBinding) :
        BaseViewHolder<Genre, ItemGenreBinding>(binding) {
        init {
            setItemWidth()
            addRootClickListener()
        }

        private fun addRootClickListener() {
            binding.root.setOnClickListener {
                val genre = getItem(adapterPosition)
                if (genre.isFavorite) {
                    mListener.onRemoveFavoriteGenreClicked(genre.id)
                    binding.favoriteImage.setImageResource(R.drawable.ic_sharp_star_border)
                    genre.isFavorite = false
                } else {
                    mListener.onAddFavoriteGenreClicked(genre.id)
                    binding.favoriteImage.setImageResource(R.drawable.ic_sharp_star)
                    genre.isFavorite = true
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

        override fun onBind(genre: Genre) {
            Glide.with(binding.root.context).load(genre.imageBackground).into(binding.genreImage)
            binding.genreName.text = genre.name
            if (!genre.isFavorite) {
                binding.favoriteImage.setImageResource(R.drawable.ic_sharp_star_border)
            } else {
                binding.favoriteImage.setImageResource(R.drawable.ic_sharp_star)
            }
        }
    }

    companion object {
        internal var callback: DiffUtil.ItemCallback<Genre> =
            object : DiffUtil.ItemCallback<Genre>() {
                override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                    return false
                }

                override fun areContentsTheSame(
                    oldItem: Genre,
                    newItem: Genre
                ): Boolean {
                    return false
                }

            }
    }
}