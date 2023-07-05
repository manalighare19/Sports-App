package com.example.sportsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.sportsapp.data.Player
import com.example.sportsapp.databinding.ItemRowBinding

class PlayersListAdapter : ListAdapter<Player, PlayerItemViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerItemViewHolder {
        return PlayerItemViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayerItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlayerItemViewHolder(private val binding: ItemRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(player: Player) {
        binding.playerName.text = player.strPlayer

        val imageUrl = player.strThumb
        Glide.with(binding.playerImage.context)
            .load(imageUrl)
            .into(binding.playerImage)
    }
}

class DiffCallback : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem == newItem
    }
}