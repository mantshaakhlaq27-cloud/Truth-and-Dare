package com.example.truthordareapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class SpinActivity : AppCompatActivity() {

    private lateinit var ivBottle: ImageView
    private lateinit var btnSpin: Button
    private lateinit var tvSelectedPlayer: TextView
    private lateinit var btnTruth: Button
    private lateinit var btnDare: Button
    private lateinit var tvDifficulty: TextView
    private lateinit var tvTeamScore: TextView
    private lateinit var tvPowerUp: TextView

    private var playerList = ArrayList<Player>()
    private var isSpinning = false
    private var selectedPlayer: Player? = null
    private var currentRotation = 0f
    private var difficulty = "Medium"
    private var teamMode = false
    private var bottleSkin = 0
    private var currentPowerUp: PowerUp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spin)

        playerList = intent.getParcelableArrayListExtra("PLAYER_LIST") ?: ArrayList()
        difficulty = intent.getStringExtra("DIFFICULTY") ?: "Medium"
        teamMode = intent.getBooleanExtra("TEAM_MODE", false)
        bottleSkin = intent.getIntExtra("BOTTLE_SKIN", 0)

        ivBottle = findViewById(R.id.ivBottle)
        btnSpin = findViewById(R.id.btnSpin)
        tvSelectedPlayer = findViewById(R.id.tvSelectedPlayer)
        btnTruth = findViewById(R.id.btnTruth)
        btnDare = findViewById(R.id.btnDare)
        tvDifficulty = findViewById(R.id.tvDifficulty)
        tvTeamScore = findViewById(R.id.tvTeamScore)
        tvPowerUp = findViewById(R.id.tvPowerUp)

        // Apply bottle skin color
        ivBottle.setColorFilter(BottleSkinManager.skins[bottleSkin].color)

        // Show difficulty
        tvDifficulty.text = "⚡ $difficulty Mode"

        // Show team scores if team mode
        if (teamMode) {
            updateTeamScores()
            tvTeamScore.visibility = View.VISIBLE
        } else {
            tvTeamScore.visibility = View.GONE
        }

        tvSelectedPlayer.visibility = View.GONE
        btnTruth.visibility = View.GONE
        btnDare.visibility = View.GONE
        tvPowerUp.visibility = View.GONE

        btnSpin.setOnClickListener {
            if (!isSpinning) {
                spinBottle()
            }
        }

        btnTruth.setOnClickListener {
            launchTruthDareActivity("TRUTH")
        }

        btnDare.setOnClickListener {
            launchTruthDareActivity("DARE")
        }

        // View Leaderboard button
        findViewById<Button>(R.id.btnViewLeaderboard).setOnClickListener {
            showLeaderboard()
        }
    }

    private fun spinBottle() {
        isSpinning = true
        btnSpin.isEnabled = false

        tvSelectedPlayer.visibility = View.GONE
        btnTruth.visibility = View.GONE
        btnDare.visibility = View.GONE
        tvPowerUp.visibility = View.GONE
        currentPowerUp = null

        // Get all effect views

        val sparkle1 = findViewById<ImageView>(R.id.ivSparkle1)
        val sparkle2 = findViewById<ImageView>(R.id.ivSparkle2)
        val sparkle3 = findViewById<ImageView>(R.id.ivSparkle3)
        val sparkle4 = findViewById<ImageView>(R.id.ivSparkle4)



        // Animate sparkles with staggered timing
        sparkle1.animate().alpha(1f).rotation(360f).setDuration(3000).setStartDelay(0).start()
        sparkle2.animate().alpha(1f).rotation(-360f).setDuration(3000).setStartDelay(200).start()
        sparkle3.animate().alpha(1f).rotation(360f).setDuration(3000).setStartDelay(400).start()
        sparkle4.animate().alpha(1f).rotation(-360f).setDuration(3000).setStartDelay(600).start()

        // Change bottle color during spin (rainbow effect)
        val colorAnimator = android.animation.ValueAnimator.ofFloat(0f, 1f)
        colorAnimator.duration = 3000
        colorAnimator.addUpdateListener { animation ->
            val fraction = animation.animatedValue as Float
            val hue = fraction * 360f
            val color = Color.HSVToColor(floatArrayOf(hue, 0.6f, 0.8f))
            ivBottle.setColorFilter(color)
        }
        colorAnimator.start()

        val randomSpins = Random.nextInt(3, 6)
        val randomDegree = Random.nextFloat() * 360
        val totalRotation = (randomSpins * 360) + randomDegree

        val rotate = RotateAnimation(
            currentRotation,
            currentRotation + totalRotation,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 3000
            fillAfter = true

            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    isSpinning = false
                    btnSpin.isEnabled = true

                    currentRotation = (currentRotation + totalRotation) % 360

                    // Fade out effects

                    sparkle1.animate().alpha(0f).setDuration(500).start()
                    sparkle2.animate().alpha(0f).setDuration(500).start()
                    sparkle3.animate().alpha(0f).setDuration(500).start()
                    sparkle4.animate().alpha(0f).setDuration(500).start()

                    // Reset bottle color to selected skin
                    ivBottle.setColorFilter(BottleSkinManager.skins[bottleSkin].color)

                    selectedPlayer = playerList[Random.nextInt(playerList.size)]

                    tvSelectedPlayer.text = "${selectedPlayer!!.emoji} It's ${selectedPlayer!!.name}'s turn!"
                    if (teamMode) {
                        tvSelectedPlayer.append("\n${selectedPlayer!!.team}")
                    }
                    tvSelectedPlayer.visibility = View.VISIBLE

                    // Pulse animation for selected player text
                    tvSelectedPlayer.animate()
                        .scaleX(1.2f)
                        .scaleY(1.2f)
                        .setDuration(300)
                        .withEndAction {
                            tvSelectedPlayer.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(300)
                                .start()
                        }
                        .start()

                    currentPowerUp = PowerUpManager.getRandomPowerUp()
                    if (currentPowerUp != null) {
                        tvPowerUp.text = "${currentPowerUp!!.emoji} ${currentPowerUp!!.name}!\n${currentPowerUp!!.description}"
                        tvPowerUp.visibility = View.VISIBLE

                        // Bounce animation for power-up
                        tvPowerUp.scaleX = 0f
                        tvPowerUp.scaleY = 0f
                        tvPowerUp.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(400)
                            .start()

                        Toast.makeText(this@SpinActivity, "🎁 Power-Up Earned! ${currentPowerUp!!.name}", Toast.LENGTH_LONG).show()
                    }

                    btnTruth.visibility = View.VISIBLE
                    btnDare.visibility = View.VISIBLE

                    // Slide in buttons
                    btnTruth.translationX = -300f
                    btnDare.translationX = 300f
                    btnTruth.animate().translationX(0f).setDuration(400).start()
                    btnDare.animate().translationX(0f).setDuration(400).start()
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }

        ivBottle.startAnimation(rotate)
    }

    private fun launchTruthDareActivity(type: String) {
        val intent = Intent(this, TruthDareActivity::class.java)
        intent.putExtra("TYPE", type)
        intent.putExtra("PLAYER", selectedPlayer)
        intent.putExtra("DIFFICULTY", difficulty)
        intent.putExtra("POWER_UP", currentPowerUp)
        intent.putExtra("TEAM_MODE", teamMode)
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            // Update player from result
            val updatedPlayer = data?.getParcelableExtra<Player>("UPDATED_PLAYER")
            if (updatedPlayer != null) {
                val index = playerList.indexOfFirst { it.name == updatedPlayer.name }
                if (index != -1) {
                    playerList[index] = updatedPlayer
                }
            }

            if (teamMode) {
                updateTeamScores()
            }
        }
    }

    private fun updateTeamScores() {
        val teamAScore = playerList.filter { it.team.contains("Team A") }.sumOf { it.score }
        val teamBScore = playerList.filter { it.team.contains("Team B") }.sumOf { it.score }
        tvTeamScore.text = "Team A 🔵: $teamAScore pts | Team B 🔴: $teamBScore pts"
    }

    private fun showLeaderboard() {
        val intent = Intent(this, LeaderboardActivity::class.java)
        intent.putParcelableArrayListExtra("PLAYER_LIST", playerList)
        intent.putExtra("TEAM_MODE", teamMode)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        tvSelectedPlayer.visibility = View.GONE
        btnTruth.visibility = View.GONE
        btnDare.visibility = View.GONE
        tvPowerUp.visibility = View.GONE
    }
}