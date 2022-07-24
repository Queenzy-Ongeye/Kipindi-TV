package dev.queen.kipindiauthentication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import dev.queen.kipindiauthentication.databinding.ActivityMainBinding

class SplashActivity : AppCompatActivity() {
    lateinit var bindingMain: ActivityMainBinding
    lateinit var lottie: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingMain.root)
        lottie = bindingMain.animationView

        startActivity(Intent(this, TeaserActivity::class.java))
//        finish()
    }
}