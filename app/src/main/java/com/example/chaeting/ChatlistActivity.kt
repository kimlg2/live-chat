package com.example.chaeting

import SpaceItemDecoration
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chaeting.Adapler.UserItem
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ChatlistActivity : AppCompatActivity() {
    private val TAG = ChatlistActivity::class.java.simpleName
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatlist)

        // RecyclerView와 GroupAdapter 설정
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val adapter = GroupAdapter<GroupieViewHolder>()

        // GridLayoutManager를 설정하여 한 줄에 3개의 아이템이 나오도록 설정
        val gridLayoutManager = GridLayoutManager(this, 2) // 열의 수를 3으로 설정
        recyclerView.layoutManager = gridLayoutManager

        // ItemDecoration 추가 (간격 설정)
        val spacing = 2 // 각 아이템 사이의 간격을 10px로 설정
        recyclerView.addItemDecoration(SpaceItemDecoration(spacing))

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val username = document.get("username").toString()
                    val uid = document.get("uid").toString()
                    val createdAt = document.getLong("createdAt") ?: 0

                    adapter.add(UserItem(username, uid, createdAt))
                }
                recyclerView.adapter = adapter
            }

        adapter.setOnItemClickListener { item, view ->
            val name: String = (item as UserItem).name
            val uid: String = (item as UserItem).uid

            val intent = Intent(this, ChatRoomActivity::class.java)
            intent.putExtra("yourUid", uid)
            intent.putExtra("name", name)

            startActivity(intent)
        }
    }
}
