package com.example.primerapracticacangrejo.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.primerapracticacangrejo.R
import com.example.primerapracticacangrejo.databinding.ActivityCompletedLoginBinding
import model.UserProvider.Companion.getUsernameByEmail

class CompletedLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompletedLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCompletedLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Turn your Toolbar into the ActionBar
        setSupportActionBar(binding.menuBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val email = intent.getStringExtra("email_key").toString();

        binding.txtUser.text = "Bienvenid@ " + getUsernameByEmail(email) + "!";
    }



    // Inflate the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu) // use your menu XML filename here
        return true
    }

    // Handle clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val email = intent.getStringExtra("email_key").toString();

        return when (item.itemId) {
            R.id.opc_change_passwd -> {
                val intent = Intent(this, PasswordRecoveryQuestionActivity::class.java)
                intent.putExtra("email_key", email)
                startActivity(intent)
                finish()
                true
            }
            R.id.opc_logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

