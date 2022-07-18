package dev.queen.kipindiauthentication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.queen.kipindiauthentication.databinding.ActivityTeaserBinding
import dev.queen.kipindiauthentication.userLogIn.RegisterActivity
import dev.queen.kipindiauthentication.userLogIn.Sign_InActivity

class TeaserActivity : AppCompatActivity() {
    lateinit var binding: ActivityTeaserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeaserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBinding()
    }

    fun setBinding(){
        binding.btnTeaserLogin.setOnClickListener {
            val intent = Intent(this, Sign_InActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}