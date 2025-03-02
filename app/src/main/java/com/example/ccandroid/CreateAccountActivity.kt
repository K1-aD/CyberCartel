package com.example.ccandroid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        databaseHelper = DatabaseHelper(this)

        val registerButton: Button = findViewById(R.id.bt_createAccount)
        val usernameEditText: EditText = findViewById(R.id.et_username_createAccountPage)
        val emailEditText: EditText = findViewById(R.id.et_email_createAccountPage)
        val passwordEditText: EditText = findViewById(R.id.et_password_createAccountPage)
        val confirmPasswordEditText: EditText = findViewById(R.id.et_Confirmpassword_createAccountPage)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (databaseHelper.registerUser(username, email, password)) {
                Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
