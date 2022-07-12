package dev.queen.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.queen.movieapp.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import dev.queen.movieapp.FirebaseUtils.firebaseAuth
import dev.queen.movieapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    lateinit var bindingHome: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHome = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bindingHome.root)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        bindingHome.btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, CreateAccountActivity::class.java))
            Toast.makeText(this, "signed out", Toast.LENGTH_LONG).show()
            finish()
        }


    }
}