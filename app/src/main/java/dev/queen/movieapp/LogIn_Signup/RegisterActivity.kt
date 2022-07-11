package dev.queen.movieapp.LogIn_Signup

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.queen.movieapp.HomeActivity
import dev.queen.movieapp.R
import dev.queen.movieapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        setClicks()

    }

    fun setClicks(){
        binding.btnRegister.setOnClickListener {
            obtainInputs()
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.tvSign.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))

        }
    }

    data class Inputs(var names: String, var email: String, var password: String, var confirmPwd: String)
    private fun obtainInputs(): Inputs? {
        var names = binding.etFullName.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPswd.text.toString()
        var confirmPwd = binding.etConfirm.text.toString()

        var error = false

        if (names.isBlank()){
            binding.tilFullName.error = "Name required"
            error = true
        }
        if (email.isBlank()){
            binding.tilEmail.error = "Email required"
            error = true
        }
        if (password.isBlank()){
            binding.tilPswd.error = "Password required"
            error = true
        }
        if (confirmPwd.isBlank()){
            binding.tilConfirm.error = "Password not confirmed"
            error = true
        }
        if (password != confirmPwd){
            Toast.makeText(applicationContext, "Password doesn't match", Toast.LENGTH_LONG).show()
        }
        if (!error){
            return Inputs(names.toString(),email.toString(), password.toString(), confirmPwd.toString())
        }
        return null

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Successfully registered", Toast.LENGTH_LONG).show()
                finish()
            }
            else{
                Toast.makeText(this, "Registration failed", Toast.LENGTH_LONG).show()
            }
        }

    }
}