package com.example.truthordareapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvWinner: TextView
    private lateinit var rvLeaderboard: RecyclerView
    private lateinit var btnClose: Button
    private lateinit var tvTeamWinner: TextView

    private var playerList = ArrayList<Player>()
    private var teamMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        playerList = intent.getParcelableArrayListExtra("PLAYER_LIST") ?: ArrayList()
        teamMode = intent.getBooleanExtra("TEAM_MODE", false)

        tvTitle = findViewById(R.id.tvLeaderboardTitle)
        tvWinner = findViewById(R.id.tvWinner)
        rvLeaderboard = findViewById(R.id.rvLeaderboard)
        btnClose = findViewById(R.id.btnClose)
        tvTeamWinner = findViewById(R.id.tvTeamWinner)

        // Sort players by score
        val sortedPlayers = playerList.sortedByDescending { it.score }

        // Show winner
        if (sortedPlayers.isNotEmpty()) {
            val winner = sortedPlayers[0]
            tvWinner.text = "👑 ${winner.emoji} ${winner.name}\n${winner.score} points"
        }

        // Show team winner if team mode
        if (teamMode) {
            val teamAScore = playerList.filter { it.team.contains("Team A") }.sumOf { it.score }
            val teamBScore = playerList.filter { it.team.contains("Team B") }.sumOf { it.score }

            val teamWinnerText = when {
                teamAScore > teamBScore -> "🏆 Team A Wins! 🔵\n$teamAScore points"
                teamBScore > teamAScore -> "🏆 Team B Wins! 🔴\n$teamBScore points"
                else -> "🤝 It's a Tie!\n$teamAScore points each"
            }

            tvTeamWinner.text = teamWinnerText
            tvTeamWinner.visibility = android.view.View.VISIBLE
        } else {
            tvTeamWinner.visibility = android.view.View.GONE
        }

        // Setup RecyclerView
        val adapter = LeaderboardAdapter(sortedPlayers)
        rvLeaderboard.layoutManager = LinearLayoutManager(this)
        rvLeaderboard.adapter = adapter

        btnClose.setOnClickListener {
            finish()
        }
    }
}