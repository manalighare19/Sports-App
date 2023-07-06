package com.example.sportsapp.ui

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportsapp.R
import com.example.sportsapp.data.Player
import com.example.sportsapp.databinding.FragmentPlayersListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersListFragment : Fragment(), RowClickListener {

    private lateinit var binding: FragmentPlayersListBinding
    private val viewModel: PlayersListViewModel by viewModels()
    private val playersListAdapter = PlayersListAdapter(this@PlayersListFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.players_list_fragment_title)
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

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search query submission
                viewModel.fetchPlayersInfo(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle search query text changes
                return false
            }
        })

        viewModel.result.observe(viewLifecycleOwner) { state ->
            binding.apply {
                when (state) {
                    is PlayersListViewModel.UiState.Data -> {
                        progressBar.visibility = View.GONE
                        playersListAdapter.submitList(state.playersList.players)
                    }
                    PlayersListViewModel.UiState.Empty -> {
                        progressBar.visibility = View.GONE
                        emptyStateView.apply {
                            visibility = View.VISIBLE
                            emptyText.text = getString(R.string.empty_text)
                            emptyImage.setImageResource(R.drawable.ic_search)
                        }
                        playersListAdapter.submitList(emptyList())
                    }
                    PlayersListViewModel.UiState.Failure -> {
                        progressBar.visibility = View.GONE
                        emptyStateView.apply {
                            visibility = View.VISIBLE
                            emptyText.text = getString(R.string.error_text)
                            emptyImage.setImageResource(R.drawable.ic_error)
                        }
                        playersListAdapter.submitList(emptyList())
                    }
                    PlayersListViewModel.UiState.Init,
                    PlayersListViewModel.UiState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        emptyStateView.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onRowClicked(playerDetails: Player) {
        findNavController().navigate(
            PlayersListFragmentDirections.actionPlayersListToPlayerDetails(playerDetails)
        )
    }
}

interface RowClickListener {
    fun onRowClicked(playerDetails: Player)
}