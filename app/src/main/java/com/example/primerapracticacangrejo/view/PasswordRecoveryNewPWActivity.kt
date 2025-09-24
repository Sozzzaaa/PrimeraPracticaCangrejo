package com.example.primerapracticacangrejo.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primerapracticacangrejo.R
import com.example.primerapracticacangrejo.databinding.ActivityPasswordRecoveryNewPwactivityBinding
import kotlinx.coroutines.*
import android.os.Handler
import android.os.Looper
import model.UserProvider

class PasswordRecoveryNewPWActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPasswordRecoveryNewPwactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPasswordRecoveryNewPwactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val email = intent.getStringExtra("email_key").toString();

        fun isValidPassword(password: String): Boolean {
            if (password.length < 8) return false
            val hasUppercase = password.any { it.isUpperCase() }
            val hasDigit = password.any { it.isDigit() }
            val hasSpecial = password.any { it in "_.,#$?@-=![]{}()+" }
            return hasUppercase && hasDigit && hasSpecial
        }

        binding.buttonLogin.setOnClickListener {
            val newPassword = binding.editTextAnswer.text.toString().trim()

            if (!isValidPassword(newPassword)) {
                Toast.makeText(
                    this,
                    "Tu contraseña no cumple los requisitos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            UserProvider.updatePassword(this, email, newPassword)  // save new password

            Toast.makeText(this, "Contraseña actualizada! Seras redireccionado a login", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({
                // Code to run after delay
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)



        }
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}