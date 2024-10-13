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
import com.example.chaeting.Model.User
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG : String = MainActivity ::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val email: EditText = findViewById<EditText>(R.id.email)
        val password: EditText = findViewById<EditText>(R.id.password)
        val signUp_btn_main: Button = findViewById<Button>(R.id.signUp_btn_main)
        val username: EditText = findViewById<EditText>(R.id.username)
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        signUp_btn_main.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()
            val usernameInput = username.text.toString()
            if (email.isEmpty() || password.isEmpty() || usernameInput.isEmpty()) {
                showAlertDialog("회원정보를 입력해주세요.")
                return@setOnClickListener
            }

            if (password.length < 5) {
                showAlertDialog("비밀번호는 5글자 이상이어야 합니다.")
                return@setOnClickListener
            } else if (usernameInput.length > 5) { // username 길이 체크
                showAlertDialog("사용자 이름은 5글자 이하여야 합니다.")
                return@setOnClickListener
            }


            else {

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {


                        Log.d(TAG, "성공")
                        val uid = FirebaseAuth.getInstance().uid ?: ""
                        val user = User(uid, username.text.toString(), System.currentTimeMillis())


                        val db = FirebaseFirestore.getInstance().collection("users")
                        db.document(uid)
                            .set(user)
                            .addOnSuccessListener {
                                Log.d(TAG,"데이터베이스 성공")
                            }
                            .addOnFailureListener {
                                Log.d(TAG,"데이터베이스 실패")
                            }
                        val  intent = Intent(this,LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {

                        Log.d(TAG, "실패", task.exception)
                    }
                }
        }
        }
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
