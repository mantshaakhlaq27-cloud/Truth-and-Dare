package com.example.truthordareapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EnhancedPlayerAdapter(
    private val players: MutableList<Player>,
    private val onPlayerClick: (Int) -> Unit
) : RecyclerView.Adapter<EnhancedPlayerAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPlayerEmoji: TextView = view.findViewById(R.id.tvPlayerEmoji)
        val tvPlayerName: TextView = view.findViewById(R.id.tvPlayerName)
        val tvPlayerTeam: TextView = view.findViewById(R.id.tvPlayerTeam)
        val tvRemove: TextView = view.findViewById(R.id.tvRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_enhanced, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.tvPlayerEmoji.text = player.emoji
        holder.tvPlayerName.text = player.name

        if (player.team.isNotEmpty()) {
            holder.tvPlayerTeam.text = player.team
            holder.tvPlayerTeam.visibility = View.VISIBLE
        } else {
            holder.tvPlayerTeam.visibility = View.GONE
        }

        holder.tvRemove.setOnClickListener {
            onPlayerClick(position)
        }
    }

    override fun getItemCount(): Int = players.size
}