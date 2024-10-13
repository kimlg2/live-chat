package com.example.chaeting.Adapler

import android.widget.TextView
import com.example.chaeting.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class UserItem(val name: String, val uid: String, val createdAt: Long) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val nameTextView = viewHolder.itemView.findViewById<TextView>(R.id.name)
        val timeTextView = viewHolder.itemView.findViewById<TextView>(R.id.time)

        nameTextView.text = name

        // 시간 포맷팅
        val formattedTime = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date(createdAt))
        timeTextView.text = formattedTime // 시간 텍스트 설정
    }

    override fun getLayout(): Int {
        return R.layout.chat_list
    }
}
