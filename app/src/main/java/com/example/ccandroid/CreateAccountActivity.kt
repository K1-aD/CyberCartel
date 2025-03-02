package com.example.ccandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        databaseHelper = DatabaseHelper(this)

        val usernameEditText: EditText = findViewById(R.id.et_username_createAccountPage)
        val emailEditText: EditText = findViewById(R.id.et_email_createAccountPage)
        val passwordEditText: EditText = findViewById(R.id.et_password_createAccountPage)
        val confirmPasswordEditText: EditText = findViewById(R.id.et_Confirmpassword_createAccountPage)
        val createAccountButton: Button = findViewById(R.id.bt_createAccount)
        val alreadyHaveAccountText: TextView = findViewById(R.id.tv_already_have_an_account_logIn)

        createAccountButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (databaseHelper.registerUser(username, email, password)) {
                Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // Close CreateAccountActivity after success
            } else {
                Toast.makeText(this, "Error Creating Account", Toast.LENGTH_SHORT).show()
            }
        }

        alreadyHaveAccountText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Close CreateAccountActivity when navigating back
        }
    }
}
