package dev.queen.kipindiauthentication.userLogIn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseUser
import dev.queen.kipindiauthentication.databinding.ActivityRegisterBinding
import dev.queen.kipindiauthentication.extensions.Extensions.toast
import dev.queen.kipindiauthentication.ui.HomeActivity
import dev.queen.kipindiauthentication.utils.FirebaseUtils.firebaseAuth
import dev.queen.kipindiauthentication.utils.FirebaseUtils.firebaseUser

class RegisterActivity : AppCompatActivity() {
    lateinit var bindingReg: ActivityRegisterBinding
    lateinit var createArray : Array<TextInputEditText>
    lateinit var userEmail : String
    lateinit var userPass : String
    override fun onCreate(savedInstanceState: Bundle?) {
        bindingReg = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingReg.root)

        createArray = arrayOf(bindingReg.etEmail,
            bindingReg.etConfirm,
            bindingReg.etPassword)
        setOnClicks()
    }

    fun setOnClicks() {
        bindingReg.btnReg.setOnClickListener {
            createUser()

        }

        bindingReg.btnLogIn.setOnClickListener {
            startActivity(Intent(this, Sign_InActivity::class.java))
            toast("Please sign into your account")
        }
    }


    //checking if a user is signed in
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = firebaseAuth.currentUser
        currentUser?.let {
            startActivity(Intent(this, HomeActivity::class.java))
            toast("Welcome back")
        }
    }

    private fun notEmpty(): Boolean = bindingReg.etEmail.text.toString().trim().isNotEmpty() &&
            bindingReg.etPassword.text.toString().trim().isNotEmpty() &&
            bindingReg.etConfirm.text.toString().trim().isNotEmpty()

    private fun identicalPassword (): Boolean{
        var identical = false
        var pass = bindingReg.etPassword.text.toString().trim()
        var confirm = bindingReg.etConfirm.text.toString().trim()

        if (notEmpty() && pass == confirm){
            identical = true
        } else if (!notEmpty()){
            createArray.forEach { textInputEditText ->
                if (textInputEditText.text.toString().trim().isEmpty()){
                    textInputEditText.error = "${textInputEditText.hint} is required"
                }
            }
        }else{
            toast("Password not matching")
        }
        return identical
    }

    private fun createUser() {
        if (identicalPassword()){
            userEmail = bindingReg.etEmail.text.toString().trim()
            userPass = bindingReg.etPassword.text.toString().trim()

            //creating a user

            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    toast("Account created successfully")
                    sendEmailVerification()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }else{
                    toast("Failed to create account")
                }
            }
        }

    }

    private fun sendEmailVerification() {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful){
                    toast("Email sent to $userEmail")
                }
            }
        }
    }
}