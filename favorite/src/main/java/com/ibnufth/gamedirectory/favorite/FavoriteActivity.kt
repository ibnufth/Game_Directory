package com.ibnufth.gamedirectory.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibnufth.gamedirectory.core.ui.GameAdapter
import com.ibnufth.gamedirectory.favorite.databinding.ActivityFavoriteBinding
import com.ibnufth.gamedirectory.ui.detail.DetailGameActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var gameAdapter: GameAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: ActivityFavoriteBinding? = null
    private val activityFavoriteBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)
        loadKoinModules(favoriteModule)
        getFavoriteData()
    }

    private fun getFavoriteData() {
        gameAdapter = GameAdapter()
        gameAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailGameActivity::class.java)
            intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData.id)
            startActivity(intent)
        }

        favoriteViewModel.favoriteGame.observe(this, { favoriteGame ->
            activityFavoriteBinding.tvEmpty.visibility = if (favoriteGame.isNullOrEmpty()) View.VISIBLE else View.GONE
            gameAdapter.setData(favoriteGame)
        })
        with(activityFavoriteBinding.rvGame) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gameAdapter
        }
    }
}