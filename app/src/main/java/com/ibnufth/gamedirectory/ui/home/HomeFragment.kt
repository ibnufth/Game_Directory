package com.ibnufth.gamedirectory.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibnufth.gamedirectory.R
import com.ibnufth.gamedirectory.core.data.Resource
import com.ibnufth.gamedirectory.core.ui.GameAdapter
import com.ibnufth.gamedirectory.databinding.FragmentHomeBinding
import com.ibnufth.gamedirectory.ui.detail.DetailGameActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var gameAdapter: GameAdapter

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val fragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameAdapter = GameAdapter()
        gameAdapter.onItemClick = {data ->
            val intent =Intent(activity, DetailGameActivity::class.java)
            intent.putExtra(DetailGameActivity.EXTRA_DATA, data.id)
            startActivity(intent)
        }

        homeViewModel.games.observe(viewLifecycleOwner, { game ->
            if (game != null) {
                when (game) {
                    is Resource.Loading<*> -> fragmentHomeBinding.progressBar.visibility =
                        View.VISIBLE
                    is Resource.Success<*> -> {
                        fragmentHomeBinding.progressBar.visibility = View.GONE
                        gameAdapter.setData(game.data)
                        Log.d(TAG, "onCreate: this isi the data${game.data}")
                    }
                    is Resource.Error<*> -> {
                        fragmentHomeBinding.progressBar.visibility = View.GONE
                        fragmentHomeBinding.errorMessage.visibility = View.VISIBLE
                        fragmentHomeBinding.errorMessage.text =
                            game.message ?: getString(R.string.error_message)
                    }
                }
            }
        })
        with(fragmentHomeBinding.rvGame) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gameAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}