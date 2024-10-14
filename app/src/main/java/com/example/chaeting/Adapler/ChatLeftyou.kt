package com.example.chaeting.Adapler

import android.widget.TextView
import com.example.chaeting.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

// msg와 name을 함께 전달받아 처리
class ChatLeftyou(val msg: String, val name: String) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val leftChatTextView: TextView = viewHolder.itemView.findViewById(R.id.left_chat)
        val nameTextView: TextView = viewHolder.itemView.findViewById(R.id.youname)

        // 메시지와 이름을 TextView에 설정
        leftChatTextView.text = msg
        nameTextView.text = name
    }

    override fun getLayout(): Int {
        return R.layout.chat_left_you
    }
}
