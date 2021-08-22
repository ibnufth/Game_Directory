package com.ibnufth.gamedirectory.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.ibnufth.gamedirectory.R
import com.ibnufth.gamedirectory.core.domain.model.Games
import com.ibnufth.gamedirectory.databinding.ActivityDetailGameBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailGameActivity : AppCompatActivity() {

    private var _binding: ActivityDetailGameBinding? = null
    private val activityDetailGameBinding get() = _binding!!

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(activityDetailGameBinding.root)

        setSupportActionBar(activityDetailGameBinding.toolbar)

        val dataGame = intent.getIntExtra(EXTRA_DATA, 0)
        showDetailGame(dataGame)
    }

    private fun showDetailGame(id :Int) {
        detailViewModel.getDetailGame(id).observe(this, { game ->
            Log.d(TAG, "showDetailGame: $game")
            supportActionBar?.title = game.title
            with(activityDetailGameBinding.detailContent) {
                tvDateRelease.text = game.releaseDate
                tvGenre.text = game.genre
                tvTitle.text = game.title
                tvOverviewItem.text = game.shortDescription
                tvDeveloper.text = game.developer
                tvGameUrl.text = game.gameUrl
                tvPublisher.text = game.publisher
                tvDeveloper.text = game.developer
                tvPlatform.text = game.platform
                Glide.with(this@DetailGameActivity)
                    .load(game.thumbnail)
                    .into(imagePoster)
                var statusFavorite = game.favorite
                setStatusFavorite(statusFavorite)
                activityDetailGameBinding.fabFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    lifecycleScope.launch {
                        detailViewModel.setFavorite(game, statusFavorite)
                    }
                    setStatusFavorite(statusFavorite)
                }
            }
        })
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            activityDetailGameBinding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_fill
                )
            )
        } else {
            activityDetailGameBinding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val TAG = "DetailGameActivity"
    }
}