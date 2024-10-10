package com.example.chaeting

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.chaeting.Model.ChatLeftyou
import com.example.chaeting.Model.ChatRightMe
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ChatRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)
        val recyclerView_chat: RecyclerView = findViewById(R.id.recyclerView_chat)
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
    }
}