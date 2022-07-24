package dev.queen.kipindiauthentication.userLogIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText
import dev.queen.kipindiauthentication.R
import dev.queen.kipindiauthentication.databinding.ActivitySignInBinding
import dev.queen.kipindiauthentication.extensions.Extensions.toast
import dev.queen.kipindiauthentication.ui.HomeActivity
import dev.queen.kipindiauthentication.utils.FirebaseUtils.firebaseAuth

class Sign_InActivity : AppCompatActivity() {
    lateinit var bindingSign: ActivitySignInBinding
    lateinit var signEmail: String
    lateinit var signPass: String
    lateinit var signArray: Array<TextInputEditText>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSign = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(bindingSign.root)

        signArray = arrayOf(
            bindingSign.etEmail,
            bindingSign.etPassword
        )

        setOnclick()
    }

    fun setOnclick() {
        bindingSign.btnCreate.setOnClickListener {
            signInUser()
        }

        bindingSign.btnLog.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun notEmpty(): Boolean =
        bindingSign.etEmail.text.toString().trim()
            .isNotEmpty() && bindingSign.etPassword.text.toString().trim().isNotEmpty()

    private fun signInUser() {
        signEmail = bindingSign.etEmail.text.toString().trim()
        signPass = bindingSign.etPassword.text.toString().trim()

        if (notEmpty()){
            firebaseAuth.signInWithEmailAndPassword(signEmail, signPass).addOnCompleteListener { signIn ->
                if (signIn.isSuccessful){
                    startActivity(Intent(this, HomeActivity::class.java))
                    toast("Signed in successfully")
                    finish()
                }else{
                    toast("Sign in failed")
                }
            }
        }else{
            signArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()){
                    input.error = "${input.hint} is required"
                }
            }
        }
    }


}