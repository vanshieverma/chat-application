package com.vanshika.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnSignup = findViewById(R.id.btnSignup)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        mAuth = FirebaseAuth.getInstance()
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            login(email, password)
        }

    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(
                        this@LoginActivity, MainActivity::class.java
                    )
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (mAuth.currentUser != null) {
            val intent = Intent(
                this@LoginActivity, MainActivity::class.java
            )
            finish()
            startActivity(intent)
        }
    }
}