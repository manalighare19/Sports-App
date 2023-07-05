package com.example.sportsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportsapp.databinding.FragmentPlayersListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersListFragment : Fragment() {

    private lateinit var binding: FragmentPlayersListBinding
    private val viewModel: PlayersListViewModel by viewModels()
    private val playersListAdapter = PlayersListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPlayersListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = playersListAdapter
        }

        viewModel.fetchPlayersInfo("arsenal")

        viewModel.result.observe(viewLifecycleOwner) { state ->
            binding.apply {
                when (state) {
                    is PlayersListViewModel.UiState.Data -> {
                        progressBar.visibility = View.GONE
                        playersListAdapter.submitList(state.playersList.players)
                    }
                    PlayersListViewModel.UiState.Empty -> {
                        progressBar.visibility = View.GONE
                        playersListAdapter.submitList(emptyList())
                    }
                    PlayersListViewModel.UiState.Failure -> {
                        progressBar.visibility = View.GONE

                    }
                    PlayersListViewModel.UiState.Init,
                    PlayersListViewModel.UiState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}