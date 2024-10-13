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
import com.example.chaeting.Model.ChatNewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
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
        recyclerView_chat.adapter = adpater
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        val readRef = database.getReference("message").child(myUid.toString()).child(yourUid.toString())
        val myRef_list = database.getReference("message-user-list")

        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                Log.d(TAG,"p0 : " + p0)
                val model = p0.getValue(ChatNewModel::class.java)
                val msg = model?.message.toString()
                val who = model?.who
                if (who=="me") {
                    adpater.add(ChatRightMe(msg))
                } else  {
                    adpater.add(ChatLeftyou(msg))
                }

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onCancelled(p0: DatabaseError) {

            }

        }
        recyclerView_chat.adapter = adpater

        readRef.addChildEventListener(childEventListener)


        Chatbutton.setOnClickListener {
            val message = ChatText.text.toString()

             val chat = ChatNewModel(myUid.toString(),yourUid.toString(),message,System.currentTimeMillis(),"me")
            myRef.child(myUid.toString()).child(yourUid.toString()).push().setValue(chat)

            val chat_get = ChatNewModel(yourUid.toString(),myUid.toString(), message,System.currentTimeMillis(),"you")
            myRef.child(yourUid.toString()).child(myUid.toString()).push().setValue(chat_get)
            myRef_list.child(myUid.toString()).child(yourUid.toString()).setValue(chat)
            ChatText.setText("")

        }
    }
}