package com.example.chaeting

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.chaeting.Model.UserItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ChatlistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatlist)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val adpater = GroupAdapter<GroupieViewHolder>()

        adpater.add(UserItem())
        adpater.add(UserItem())
        adpater.add(UserItem())
        adpater.add(UserItem())

        recyclerView.adapter = adpater
    }
}