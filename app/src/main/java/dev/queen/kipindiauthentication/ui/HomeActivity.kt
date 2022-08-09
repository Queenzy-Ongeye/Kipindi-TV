package dev.queen.kipindiauthentication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import dev.queen.kipindiauthentication.R
import dev.queen.kipindiauthentication.databinding.ActivityHomeBinding
import dev.queen.kipindiauthentication.fragments.FavsFragment
import dev.queen.kipindiauthentication.fragments.HomeFragment
import dev.queen.kipindiauthentication.fragments.LatestFragment

class HomeActivity : AppCompatActivity() {
    lateinit var bindingHome: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHome = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bindingHome.root)
        setUpBtmNav()
    }

    fun setUpBtmNav() {
        bindingHome.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeMenu -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, HomeFragment())
                        .commit()
                    true
                }

                R.id.favs -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, FavsFragment())
                        .commit()
                    true
                }

                R.id.latest -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, LatestFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}