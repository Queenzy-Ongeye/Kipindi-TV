package dev.queen.kipindiauthentication.extensions

import android.app.Activity
import android.widget.Toast

object Extensions {
    fun Activity.toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}