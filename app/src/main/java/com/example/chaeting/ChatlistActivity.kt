package com.example.chaeting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.chaeting.Adapler.UserItem
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ChatlistActivity : AppCompatActivity() {
    private  val TAG = ChatlistActivity::class.java.simpleName
    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatlist)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val adpater = GroupAdapter<GroupieViewHolder>()

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    adpater.add(UserItem(document.get("username").toString(), document.get("uid").toString()))

                }
                recyclerView.adapter = adpater
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        adpater.setOnItemClickListener { item, view ->

            val name : String = (item as UserItem).name
            val uid : String = (item as UserItem).uid

            val intent = Intent(this, ChatRoomActivity::class.java)
            intent.putExtra("yourUid",uid)
            intent.putExtra("name",name)

            startActivity(intent)
        }
    }
}