package dev.queen.movieapp.LogIn_Signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import dev.queen.movieapp.CreateAccountActivity
import dev.queen.movieapp.FirebaseUtils.firebaseAuth
import dev.queen.movieapp.HomeActivity
import dev.queen.movieapp.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    lateinit var bindingLogIn: ActivityLogInBinding
    lateinit var signInArray: Array<EditText>
    lateinit var signEmail: String
    lateinit var signPass: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogIn = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(bindingLogIn.root)
        signInArray = arrayOf(
            bindingLogIn.etSignInEmail,
            bindingLogIn.etSignInEmail
        )
        setClicks()
    }

    fun setClicks(){
        bindingLogIn.btnSignIn.setOnClickListener {
            signInUser()
        }

        bindingLogIn.btnCreateAccount2.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }
    }

    private fun notEmpty(): Boolean = bindingLogIn.etSignInEmail.text.toString().trim().isNotEmpty() && bindingLogIn.etSignInPassword.text.toString().trim().isEmpty()

    private fun signInUser(){
        signEmail = bindingLogIn.etSignInEmail.text.toString().trim()
        signPass = bindingLogIn.etSignInPassword.text.toString().trim()

        if (notEmpty()){
            firebaseAuth.signInWithEmailAndPassword(signEmail, signPass).addOnCompleteListener { signIn->
                if (signIn.isSuccessful) {
                    startActivity(Intent(this, HomeActivity::class.java))
                    Toast.makeText(this, "Sign In successfull", Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(this, "Sign In was unsuccessful", Toast.LENGTH_LONG).show()
                }
            }
        }else {
            signInArray.forEach { input ->
                if (input.toString().isEmpty()){
                    input.error = "${input.hint} is required"
                }
            }
        }
    }

}