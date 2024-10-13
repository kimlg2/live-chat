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
import androidx.appcompat.app.AlertDialog
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
            if (email.isEmpty() || password.isEmpty()) {
                showAlertDialog("이메일과 비밀번호를 입력해주세요.")
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        Log.d(TAG, "성공")
                        val  intent = Intent(this,ChatlistActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                       startActivity(intent)
                    } else {

                        Log.w(TAG, "실패", task.exception)
                        showAlertDialog("이메일 또는 비밀번호를 올바르게 입력해 주세요.")
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
    private fun showAlertDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("알림")
        builder.setMessage(message)
        builder.setPositiveButton("확인") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}