package com.example.chaeting.Adapler

import android.widget.TextView
import com.example.chaeting.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class ChatLeftyou(val msg: String) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val leftChatTextView: TextView = viewHolder.itemView.findViewById(R.id.left_chat)

        leftChatTextView.text = msg
    }

    override fun getLayout(): Int {
        return R.layout.chat_left_you
    }
}