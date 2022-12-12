package com.exchange.listners

import androidx.biometric.BiometricPrompt

/**
 * Common interface to listen to Biometric Authentication callbacks
 */
interface BiometricAuthListener {
    fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult)
    fun onBiometricAuthenticationError(errorCode: Int, errorMessage: String)
}