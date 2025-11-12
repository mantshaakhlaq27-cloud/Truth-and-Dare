package com.example.truthordareapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LeaderboardAdapter(
    private val players: List<Player>
) : RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

    class LeaderboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRank: TextView = view.findViewById(R.id.tvRank)
        val tvPlayerEmoji: TextView = view.findViewById(R.id.tvPlayerEmoji)
        val tvPlayerName: TextView = view.findViewById(R.id.tvPlayerName)
        val tvPlayerStats: TextView = view.findViewById(R.id.tvPlayerStats)
        val tvPlayerScore: TextView = view.findViewById(R.id.tvPlayerScore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_leaderboard, parent, false)
        return LeaderboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val player = players[position]

        // Rank with medal
        holder.tvRank.text = when (position) {
            0 -> "🥇"
            1 -> "🥈"
            2 -> "🥉"
            else -> "#${position + 1}"
        }

        holder.tvPlayerEmoji.text = player.emoji
        holder.tvPlayerName.text = player.name
        holder.tvPlayerStats.text = "✅ ${player.completedChallenges} completed | ⏭ ${player.skippedChallenges} skipped"
        holder.tvPlayerScore.text = "${player.score} pts"
    }

    override fun getItemCount(): Int = players.size
}