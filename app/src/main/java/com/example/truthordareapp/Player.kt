package com.example.truthordareapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val name: String,
    val emoji: String,
    var score: Int = 0,
    var completedChallenges: Int = 0,
    var skippedChallenges: Int = 0,
    var team: String = ""
) : Parcelable