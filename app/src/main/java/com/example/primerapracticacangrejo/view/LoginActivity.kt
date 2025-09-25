package com.example.primerapracticacangrejo.view

import android.content.Context
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
import java.io.File


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    /*private fun copyJsonToInternal(context: Context) {
        val file = File(context.filesDir, "users.json")
        if (!file.exists()) {
            context.assets.open("users.json").use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        UserProvider.copyJsonToInternal(this)
        UserProvider.readUsersFromAssets(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UserProvider.readUsersFromAssets(this)
        UserProvider.listU.forEach {
            Log.d("LoginDebug", "User: ${it.email}, Pass: ${it.passwd}")
        }


        binding.textViewOlvidCont.setOnClickListener {
            val intent = Intent(this, PasswordReccoveryEmailActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()


            UserProvider.listU.forEach {
                Log.d("LoginDebug", "User: ${it.email}, Pass: ${it.passwd}")
            }
            // Load users
            UserProvider.readUsersFromAssets(this)

            if (!UserProvider.isValidEmail(email)) {
                Toast.makeText(this, "Favor de Ingresar un correo valido!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
            {
                // Validate
                val isValid = UserProvider.validateEmailPw(email, password)

                if (isValid) {
                    val intent = Intent(this, CompletedLoginActivity::class.java)
                    intent.putExtra("email_key", email)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Email o password invalidos!", Toast.LENGTH_SHORT).show()
                }
            }


        }

    }
}
