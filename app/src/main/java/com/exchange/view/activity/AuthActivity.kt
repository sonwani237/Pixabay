package com.exchange.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import com.exchange.R
import com.exchange.listners.BiometricAuthListener
import com.exchange.utils.BiometricUtil

class AuthActivity : AppCompatActivity(), BiometricAuthListener {

    private lateinit var buttonBiometricsLogin: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        buttonBiometricsLogin = findViewById(R.id.buttonBiometricsLogin)
        //button visibility
        showBiometricLoginOption()
    }

    fun onClickBiometrics(view: View) {
        BiometricUtil.showBiometricPrompt(
            activity = this, listener = this, cryptoObject = null, allowDeviceCredential = true
        )
    }

    override fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult) {
        Toast.makeText(this, "Biometric success", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, Dashboard::class.java))
        finish()
    }

    override fun onBiometricAuthenticationError(errorCode: Int, errorMessage: String) {
        Toast.makeText(this, "Biometric login. Error: $errorMessage", Toast.LENGTH_SHORT).show()
    }

    private fun showBiometricLoginOption() {
        if (BiometricUtil.isBiometricReady(this)) {
            buttonBiometricsLogin.visibility = View.VISIBLE
        } else {
            buttonBiometricsLogin.visibility = View.GONE
            Toast.makeText(this, "Biometric sensor not found", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Dashboard::class.java))
            finish()
        }
    }

}