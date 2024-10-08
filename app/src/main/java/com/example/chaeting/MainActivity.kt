package com.example.chaeting

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login_btn_main: Button = findViewById<Button>(R.id.login_btn_main)
        login_btn_main.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            Log.d("MainActivity", "Starting LoginActivity")
            startActivity(intent)
        }


        val logoImageView: ImageView = findViewById(R.id.logo_image)
        logoImageView.setColorFilter(Color.parseColor("#C1BDDA"), PorterDuff.Mode.SRC_IN);

    }
}
