package com.example.truthordareapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PowerUp(
    val name: String,
    val description: String,
    val emoji: String
) : Parcelable

object PowerUpManager {
    val availablePowerUps = listOf(
        PowerUp("Skip Card", "Skip your turn once", "⏭"),
        PowerUp("Double Points", "Earn double points this round", "⭐"),
        PowerUp("Swap Turn", "Swap with any other player", "🔄"),
        PowerUp("Easy Mode", "Get an easier challenge", "😌"),
        PowerUp("Wild Card", "Create your own dare", "🎴")
    )

    fun getRandomPowerUp(): PowerUp? {
        // 30% chance to get a power-up
        return if (kotlin.random.Random.nextInt(100) < 30) {
            availablePowerUps.random()
        } else {
            null
        }
    }
}