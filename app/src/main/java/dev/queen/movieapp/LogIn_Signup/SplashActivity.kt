package dev.queen.movieapp.LogIn_Signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import dev.queen.movieapp.R

class SplashActivity : AppCompatActivity() {
    lateinit var lottie: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lottie = findViewById(R.id.lottie)
        startActivity(Intent(this, TeaserActivity::class.java))
    }
}