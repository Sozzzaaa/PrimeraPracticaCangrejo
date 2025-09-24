package com.example.primerapracticacangrejo.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.example.primerapracticacangrejo.databinding.ActivityPasswordReccoveryEmailBinding
import model.UserProvider

class PasswordReccoveryEmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPasswordReccoveryEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPasswordReccoveryEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()


        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()

            if (!UserProvider.isValidEmail(email)) {
                Toast.makeText(this, "Favor de Ingresar un correo valido!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            else
            {
                if (UserProvider.emailExists(email))
                {
                    val intent = Intent(this, PasswordRecoveryQuestionActivity::class.java)
                    intent.putExtra("email_key", email)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this, "El correo ingresado no corresponde a ninguna cuenta", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
            }
        }



    }
}