package dev.queen.movieapp.LogIn_Signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import dev.queen.movieapp.HomeActivity
import dev.queen.movieapp.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    lateinit var bindingLogIn: ActivityLogInBinding
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogIn = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(bindingLogIn.root)
        mAuth = FirebaseAuth.getInstance()
        setClicks()
    }

    fun setClicks(){
        bindingLogIn.btnLogIn.setOnClickListener {
            obtainInputs()
            startActivity(Intent(this, HomeActivity::class.java))
        }

        bindingLogIn.tvrreg.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    data class UserInputs(var email: String, var password: String)
    fun obtainInputs(): UserInputs?{
        var email = bindingLogIn.etUser.text.toString()
        var pswd = bindingLogIn.etPassword.text.toString()
        var error = false

        if (email.isBlank()){
            bindingLogIn.tilUser.error = "Email required"
            error = true
        }

        if (pswd.isBlank()){
            bindingLogIn.tilPassword.error = "Password required"
            error = true
        }
        if (!error){
            return UserInputs(email.toString(), pswd.toString())
        }
        return null

        mAuth.createUserWithEmailAndPassword(email, pswd).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Successfully signed In", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Sign in Failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}