package com.example.truthordareapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class TruthDareActivity : AppCompatActivity() {

    private lateinit var tvPlayerName: TextView
    private lateinit var tvType: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var btnCompleted: Button
    private lateinit var btnSkipped: Button
    private lateinit var tvPowerUpInfo: TextView

    private var player: Player? = null
    private var difficulty = "Medium"
    private var type = "TRUTH"
    private var powerUp: PowerUp? = null
    private var teamMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_truth_dare)

        // Get data from Intent
        player = intent.getParcelableExtra("PLAYER")
        type = intent.getStringExtra("TYPE") ?: "TRUTH"
        difficulty = intent.getStringExtra("DIFFICULTY") ?: "Medium"
        powerUp = intent.getParcelableExtra("POWER_UP")
        teamMode = intent.getBooleanExtra("TEAM_MODE", false)

        // Initialize views
        tvPlayerName = findViewById(R.id.tvPlayerName)
        tvType = findViewById(R.id.tvType)
        tvQuestion = findViewById(R.id.tvQuestion)
        btnCompleted = findViewById(R.id.btnCompleted)
        btnSkipped = findViewById(R.id.btnSkipped)
        tvPowerUpInfo = findViewById(R.id.tvPowerUpInfo)

        // Display player info
        tvPlayerName.text = "${player?.emoji} ${player?.name}'s Turn"
        if (teamMode && player?.team?.isNotEmpty() == true) {
            tvPlayerName.append("\n${player?.team}")
        }

        // Display power-up if available
        if (powerUp != null) {
            tvPowerUpInfo.text = "🎁 Active Power-Up: ${powerUp!!.name}"
            tvPowerUpInfo.visibility = android.view.View.VISIBLE
        } else {
            tvPowerUpInfo.visibility = android.view.View.GONE
        }

        // Display Truth or Dare
        if (type == "TRUTH") {
            tvType.text = "❓ TRUTH"
            tvType.setTextColor(getColor(android.R.color.holo_blue_dark))

            val truths = DataProvider.getTruths(difficulty)
            val randomTruth = truths[Random.nextInt(truths.size)]
            tvQuestion.text = randomTruth
        } else {
            tvType.text = "⚡ DARE"
            tvType.setTextColor(getColor(android.R.color.holo_red_dark))

            val dares = DataProvider.getDares(difficulty)
            val randomDare = dares[Random.nextInt(dares.size)]
            tvQuestion.text = randomDare
        }

        // Completed button
        btnCompleted.setOnClickListener {
            showCompletionDialog(true)
        }

        // Skipped button
        btnSkipped.setOnClickListener {
            showCompletionDialog(false)
        }
    }

    private fun showCompletionDialog(completed: Boolean) {
        val points = calculatePoints(completed)

        val message = if (completed) {
            player?.completedChallenges = (player?.completedChallenges ?: 0) + 1
            player?.score = (player?.score ?: 0) + points
            "🎉 Great job!\n\n+$points points"
        } else {
            player?.skippedChallenges = (player?.skippedChallenges ?: 0) + 1
            "😅 No worries!\n\n0 points"
        }

        AlertDialog.Builder(this)
            .setTitle(if (completed) "Challenge Completed!" else "Challenge Skipped")
            .setMessage("${player?.emoji} ${player?.name}\n$message\n\nTotal Score: ${player?.score} pts")
            .setPositiveButton("Next Round") { _, _ ->
                finishWithResult()
            }
            .setCancelable(false)
            .show()
    }

    private fun calculatePoints(completed: Boolean): Int {
        if (!completed) return 0

        var points = if (type == "TRUTH") 10 else 20

        // Difficulty bonus
        points += when (difficulty) {
            "Easy" -> 0
            "Medium" -> 5
            "Hard" -> 10
            else -> 0
        }

        // Power-up bonus
        if (powerUp?.name == "Double Points") {
            points *= 2
        }

        return points
    }

    private fun finishWithResult() {
        val resultIntent = Intent()
        resultIntent.putExtra("UPDATED_PLAYER", player)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}