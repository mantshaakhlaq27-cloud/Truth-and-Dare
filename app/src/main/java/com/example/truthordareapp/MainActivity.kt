package com.example.truthordareapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var etPlayerName: EditText
    private lateinit var btnAddPlayer: Button
    private lateinit var btnStartGame: Button
    private lateinit var rvPlayers: RecyclerView
    private lateinit var spinnerDifficulty: Spinner
    private lateinit var switchTeamMode: Switch
    private lateinit var spinnerBottleSkin: Spinner

    private val playerList = mutableListOf<Player>()
    private lateinit var playerAdapter: EnhancedPlayerAdapter
    private var selectedDifficulty = "Medium"
    private var teamMode = false
    private var selectedBottleSkin = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        etPlayerName = findViewById(R.id.etPlayerName)
        btnAddPlayer = findViewById(R.id.btnAddPlayer)
        btnStartGame = findViewById(R.id.btnStartGame)
        rvPlayers = findViewById(R.id.rvPlayers)
        spinnerDifficulty = findViewById(R.id.spinnerDifficulty)
        switchTeamMode = findViewById(R.id.switchTeamMode)
        spinnerBottleSkin = findViewById(R.id.spinnerBottleSkin)

        // Setup Difficulty Spinner
        val difficulties = arrayOf("Easy", "Medium", "Hard")
        val difficultyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, difficulties)
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDifficulty.adapter = difficultyAdapter
        spinnerDifficulty.setSelection(1) // Default: Medium

        spinnerDifficulty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedDifficulty = difficulties[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Setup Bottle Skin Spinner
        val bottleSkinNames = BottleSkinManager.skins.map { "${it.emoji} ${it.name}" }
        val skinAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bottleSkinNames)
        skinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBottleSkin.adapter = skinAdapter

        spinnerBottleSkin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedBottleSkin = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Team Mode Switch
        switchTeamMode.setOnCheckedChangeListener { _, isChecked ->
            teamMode = isChecked
            if (isChecked && playerList.size > 0) {
                assignTeams()
            }
        }

        // Setup RecyclerView
        playerAdapter = EnhancedPlayerAdapter(playerList) { position ->
            playerList.removeAt(position)
            playerAdapter.notifyItemRemoved(position)
            if (teamMode) assignTeams()
            updateStartButtonState()
        }
        rvPlayers.layoutManager = LinearLayoutManager(this)
        rvPlayers.adapter = playerAdapter

        // Add Player button
        btnAddPlayer.setOnClickListener {
            val playerName = etPlayerName.text.toString().trim()

            when {
                playerName.isEmpty() -> {
                    Toast.makeText(this, "Please enter a player name", Toast.LENGTH_SHORT).show()
                }
                playerList.size >= 8 -> {
                    Toast.makeText(this, "Maximum 8 players allowed", Toast.LENGTH_SHORT).show()
                }
                playerList.any { it.name == playerName } -> {
                    Toast.makeText(this, "Player name already exists", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    showEmojiPickerDialog(playerName)
                }
            }
        }

        // Start Game button
        btnStartGame.setOnClickListener {
            if (playerList.size >= 2) {
                val intent = Intent(this, SpinActivity::class.java)
                intent.putParcelableArrayListExtra("PLAYER_LIST", ArrayList(playerList))
                intent.putExtra("DIFFICULTY", selectedDifficulty)
                intent.putExtra("TEAM_MODE", teamMode)
                intent.putExtra("BOTTLE_SKIN", selectedBottleSkin)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Add at least 2 players to start", Toast.LENGTH_SHORT).show()
            }
        }

        updateStartButtonState()
    }

    private fun showEmojiPickerDialog(playerName: String) {
        val emojis = arrayOf("😀", "😎", "🤓", "😇", "🥳", "🤠", "👻", "🤖", "👽", "🦸",
            "🧙", "🧛", "🧟", "🦄", "🐶", "🐱", "🐼", "🐯", "🦁", "🐸")

        AlertDialog.Builder(this)
            .setTitle("Choose Your Avatar")
            .setItems(emojis) { _, which ->
                val player = Player(playerName, emojis[which])
                playerList.add(player)
                if (teamMode) assignTeams()
                playerAdapter.notifyItemInserted(playerList.size - 1)
                etPlayerName.text.clear()
                updateStartButtonState()
                Toast.makeText(this, "${emojis[which]} $playerName added!", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun assignTeams() {
        for (i in playerList.indices) {
            playerList[i].team = if (i % 2 == 0) "Team A 🔵" else "Team B 🔴"
        }
        playerAdapter.notifyDataSetChanged()
    }

    private fun updateStartButtonState() {
        btnStartGame.isEnabled = playerList.size >= 2
        btnStartGame.alpha = if (playerList.size >= 2) 1.0f else 0.5f
    }
}