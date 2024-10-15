package com.example.chaeting

import SpaceItemDecoration
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
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

    private lateinit var adapter: GroupAdapter<GroupieViewHolder>
    private var allUsers = mutableListOf<UserItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatlist)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        adapter = GroupAdapter()

        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager

        val spacing = 2
        recyclerView.addItemDecoration(SpaceItemDecoration(spacing))


        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val username = document.get("username").toString()
                    val uid = document.get("uid").toString()
                    val createdAt = document.getLong("createdAt") ?: 0

                    val userItem = UserItem(username, uid, createdAt)
                    allUsers.add(userItem)
                }
                adapter.update(allUsers)
                recyclerView.adapter = adapter
            }


        val searchView: SearchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterUsers(newText.orEmpty())
                return true
            }
        })


        adapter.setOnItemClickListener { item, view ->
            val name: String = (item as UserItem).name
            val uid: String = (item as UserItem).uid

            val intent = Intent(this, ChatRoomActivity::class.java)
            intent.putExtra("yourUid", uid)
            intent.putExtra("name", name)

            startActivity(intent)
        }
    }


    private fun filterUsers(query: String) {
        val filteredList = if (query.isEmpty()) {
            allUsers
        } else {
            allUsers.filter { it.name.contains(query, ignoreCase = true) }
        }
        adapter.update(filteredList)
    }
}
