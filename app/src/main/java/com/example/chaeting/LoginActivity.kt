package com.example.chaeting

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        val signUp_btn: Button = findViewById<Button>(R.id.signUp_btn)
        val login_btn: Button = findViewById<Button>(R.id.login_btn)
        val login_email: EditText = findViewById<EditText>(R.id.login_email)
        val login_pwd: EditText = findViewById<EditText>(R.id.login_pwd)
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
        login_btn.setOnClickListener {
            val email = login_email.text.toString()
            val password = login_pwd.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "성공")

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "실패", task.exception)

                    }
                }


        }

        signUp_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        val logoImageView: ImageView = findViewById(R.id.logo_image)
        logoImageView.setColorFilter(Color.parseColor("#C1BDDA"), PorterDuff.Mode.SRC_IN);

    }
}