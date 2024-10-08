package com.example.chaeting

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG : String = MainActivity ::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login_btn_main: Button = findViewById<Button>(R.id.login_btn_main)
        val email: EditText = findViewById<EditText>(R.id.email)
        val password: EditText = findViewById<EditText>(R.id.password)
        val signUp_btn_main: Button = findViewById<Button>(R.id.signUp_btn_main)
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        signUp_btn_main.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()

            if(password.length < 5) {
                showAlertDialog("비밀번호는 5글자 이상이어야 합니다.")
            } else {

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "성공")
                    } else {
                        Log.d(TAG, "실패", task.exception)
                    }
                }
        }
        }



        login_btn_main.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
        }

        // 로고 이미지 색상 변경
        val logoImageView: ImageView = findViewById(R.id.logo_image)
        logoImageView.setColorFilter(Color.parseColor("#C1BDDA"), PorterDuff.Mode.SRC_IN)
    }

    private fun showAlertDialog(massage: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("알림")
        builder.setMessage(massage)
        builder.setPositiveButton("확인") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}
