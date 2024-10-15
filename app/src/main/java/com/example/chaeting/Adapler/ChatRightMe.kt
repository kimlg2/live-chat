package com.example.chaeting.Adapler

import android.widget.TextView
import com.example.chaeting.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class ChatRightMe(val msg: String) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        val rightMsgTextView: TextView = viewHolder.itemView.findViewById(R.id.right_msg)

        rightMsgTextView.text = msg
    }

    // getLayout은 파라미터 없이 리소스 ID를 반환
    override fun getLayout(): Int {
        return R.layout.chat_right_me
    }
}