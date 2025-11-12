package com.example.truthordareapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter(
    private val players: MutableList<String>,
    private val onPlayerClick: (Int) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPlayerName: TextView = view.findViewById(R.id.tvPlayerName)
        val tvPlayerNumber: TextView = view.findViewById(R.id.tvPlayerNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val playerName = players[position]
        holder.tvPlayerName.text = playerName
        holder.tvPlayerNumber.text = "P${position + 1}"

        holder.itemView.setOnClickListener {
            onPlayerClick(position)
        }
    }

    override fun getItemCount(): Int = players.size
}