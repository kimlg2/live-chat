package com.example.chaeting

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG : String = MainActivity ::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Firebase 초기화
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        // 이메일과 비밀번호로 사용자 생성
        auth.createUserWithEmailAndPassword("abc@naver.com", "123456")
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "성공")
                } else {
                    Log.d(TAG, "실패", task.exception)
                }
            }

        // 로그인 버튼 클릭 리스너
        val login_btn_main: Button = findViewById<Button>(R.id.login_btn_main)
        login_btn_main.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            Log.d("MainActivity", "Starting LoginActivity")
            startActivity(intent)
        }

        // 로고 이미지 색상 변경
        val logoImageView: ImageView = findViewById(R.id.logo_image)
        logoImageView.setColorFilter(Color.parseColor("#C1BDDA"), PorterDuff.Mode.SRC_IN)
    }
}
