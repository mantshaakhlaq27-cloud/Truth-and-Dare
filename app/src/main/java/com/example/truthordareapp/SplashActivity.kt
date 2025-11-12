package com.example.truthordareapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Find views
        val logoImage = findViewById<ImageView>(R.id.ivSplashLogo)
        val appName = findViewById<TextView>(R.id.tvSplashAppName)
        val tagline = findViewById<TextView>(R.id.tvSplashTagline)

        // Load animations
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        val slideUp = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)

        // Apply animations
        logoImage.startAnimation(fadeIn)
        appName.startAnimation(slideUp)
        tagline.startAnimation(fadeIn)

        // Wait 3 seconds then go to MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close splash screen
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}