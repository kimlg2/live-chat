package com.example.chaeting

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.chaeting.Adapler.ChatLeftyou
import com.example.chaeting.Adapler.ChatRightMe
import com.example.chaeting.Model.ChatModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ChatRoomActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val TAG = ChatlistActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)
        val recyclerView_chat: RecyclerView = findViewById(R.id.recyclerView_chat)
        val Chatbutton: Button = findViewById<Button>(R.id.Chatbutton)
        val ChatText: EditText = findViewById<EditText>(R.id.ChatText)
        auth = FirebaseAuth.getInstance()

        val  myUid = auth.uid
        val  yourUid = intent.getStringExtra("yourUid")
        val  name  = intent.getStringExtra("name")

        val adpater = GroupAdapter<GroupieViewHolder>()

        adpater.add(ChatLeftyou())
        adpater.add(ChatLeftyou())
        adpater.add(ChatLeftyou())
        adpater.add(ChatLeftyou())
        adpater.add(ChatRightMe())
        adpater.add(ChatRightMe())
        adpater.add(ChatRightMe())
        adpater.add(ChatRightMe())

        recyclerView_chat.adapter = adpater

        val db = FirebaseFirestore.getInstance()
        Chatbutton.setOnClickListener {
            val message :String = ChatText.text.toString()
            ChatText.setText("")

            val chat = ChatModel(myUid.toString() ,yourUid.toString(),message)
                db.collection("message")
                    .add(chat)
                    .addOnSuccessListener {
                       Log.d(TAG, "성공")
                    }
                    .addOnFailureListener {
                        Log.d(TAG, "실패")
                    }
        }
    }
}