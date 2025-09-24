package com.example.primerapracticacangrejo.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primerapracticacangrejo.R
import com.example.primerapracticacangrejo.databinding.ActivityMainBinding
import model.UserProvider


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UserProvider.readUsersFromAssets(this)
        UserProvider.listU.forEach {
            Log.d("LoginDebug", "User: ${it.email}, Pass: ${it.passwd}")
        }
        fun isValidEmail(email: String): Boolean {
            val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
            return emailRegex.matches(email)
        }



        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()

           // Log.d("LoginDebug", "Input email: '$email', Input password: '$password'")

            UserProvider.listU.forEach {
                Log.d("LoginDebug", "User: ${it.email}, Pass: ${it.passwd}")
            }
            // Load users
            UserProvider.readUsersFromAssets(this)

            if (!isValidEmail(email)) {
                Toast.makeText(this, "Favor de Ingresar un correo valido!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
            {
                // Validate
                val isValid = UserProvider.validateEmailPw(email, password)

                if (isValid) {
                    val intent = Intent(this, CompletedLoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Email o password invalidos!", Toast.LENGTH_SHORT).show()
                }
            }


        }

    }
}
