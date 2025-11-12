package com.example.truthordareapp

data class BottleSkin(
    val name: String,
    val color: Int,
    val emoji: String
)

object BottleSkinManager {
    val skins = listOf(
        BottleSkin("Classic Green", 0xFF4CAF50.toInt(), "🍾"),
        BottleSkin("Ruby Red", 0xFFE91E63.toInt(), "💎"),
        BottleSkin("Ocean Blue", 0xFF2196F3.toInt(), "🌊"),
        BottleSkin("Golden", 0xFFFFD700.toInt(), "⭐"),
        BottleSkin("Purple Magic", 0xFF9C27B0.toInt(), "🔮"),
        BottleSkin("Orange Sunset", 0xFFFF9800.toInt(), "🌅")
    )
}