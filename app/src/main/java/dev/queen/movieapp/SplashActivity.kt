package dev.queen.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView

class SplashActivity : AppCompatActivity() {
    lateinit var lottie: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lottie = findViewById(R.id.lottie)
//        startActivity(Intent(this, LogIn::class.java))

    }
}