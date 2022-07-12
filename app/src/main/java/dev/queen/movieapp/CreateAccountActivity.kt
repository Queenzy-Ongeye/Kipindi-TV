package dev.queen.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import dev.queen.movieapp.FirebaseUtils.firebaseAuth
import dev.queen.movieapp.FirebaseUtils.firebaseUser
import dev.queen.movieapp.LogIn_Signup.LogIn
import dev.queen.movieapp.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {
    lateinit var bindingCreate: ActivityCreateAccountBinding
    lateinit var createAccountInputArray: Array<EditText>
    lateinit var createEmail: String
    lateinit var createPass: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingCreate = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(bindingCreate.root)
        createAccountInputArray = arrayOf(
            bindingCreate.etEmail,
            bindingCreate.etPassword,
            bindingCreate.etConfirmPassword
        )
        setOnclicks()
    }

    fun setOnclicks() {
        bindingCreate.btnCreateAccount.setOnClickListener {
            register()
        }

        bindingCreate.btnSignIn2.setOnClickListener {
            startActivity(Intent(this, LogIn::class.java))
            Toast.makeText(this, "please sign into your account", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, HomeActivity::class.java))
            Toast.makeText(this, "Welcome back", Toast.LENGTH_LONG).show()
        }
    }

    private fun notEmpty(): Boolean = bindingCreate.etEmail.text.toString().trim().isNotEmpty() &&
            bindingCreate.etPassword.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        var pass = bindingCreate.etPassword.text.toString().trim()
        var confrim = bindingCreate.etConfirmPassword.text.toString().trim()
        var identical = false
        if (notEmpty() && pass == confrim) {
            identical = true
        } else if (!notEmpty()) {
            createAccountInputArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        } else {
            Toast.makeText(this, "Passwords not matching", Toast.LENGTH_LONG).show()
        }
        return identical
    }

    private fun register() {

        if (identicalPassword()) {
            // identicalPassword() returns true only  when inputs are not empty and passwords are identical
            createEmail = bindingCreate.etEmail.toString().trim()
            createPass = bindingCreate.etPassword.toString().trim()

            //create a user
            firebaseAuth.createUserWithEmailAndPassword(createEmail, createPass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Account created successfully", Toast.LENGTH_LONG)
                            .show()
                        sendEmailVerification()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Failed to Authenticate", Toast.LENGTH_LONG).show()
                    }
                }

        }

    }

    private fun sendEmailVerification() {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "email sent to $createEmail", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}

