package com.example.primerapracticacangrejo.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primerapracticacangrejo.R
import com.example.primerapracticacangrejo.databinding.ActivityPasswordRecoveryQuestionBinding
import model.UserProvider
class PasswordRecoveryQuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPasswordRecoveryQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordRecoveryQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        val email = intent.getStringExtra("email_key").toString();
        binding.textViewSubtitulo2.text = UserProvider.getQuestionByEmail(email)

        binding.buttonLogin.setOnClickListener {
            val userAnswer = binding.editTextAnswer.text.toString()
                .trim()  // remove leading/trailing spaces
                .lowercase() // convert to lowercase


            val correctAnswer = UserProvider.getAnswerByEmail(email)
            if (correctAnswer != null) {
                val normalizedCorrect = correctAnswer.trim().lowercase()

                if (userAnswer == normalizedCorrect) {
                    val intent = Intent(this, PasswordRecoveryNewPWActivity::class.java)
                    intent.putExtra("email_key", email)
                    startActivity(intent)
                    finish()
                    } else {
                        Toast.makeText(this, "Respuesta incorrecta 😎", Toast.LENGTH_SHORT).show()
                    }
                }

        }



    }
}