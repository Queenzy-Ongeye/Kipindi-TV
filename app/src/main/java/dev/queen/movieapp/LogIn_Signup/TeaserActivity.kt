package dev.queen.movieapp.LogIn_Signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.queen.movieapp.CreateAccountActivity
import dev.queen.movieapp.databinding.ActivityTeaserBinding

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
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }
}