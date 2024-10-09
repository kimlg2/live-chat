package com.example.chaeting.Model

import android.widget.TextView
import com.example.chaeting.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class UserItem(val name:String) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val nameTextView = viewHolder.itemView.findViewById<TextView>(R.id.name)
        nameTextView.text = name
    }

    override fun getLayout(): Int {
        return R.layout.chat_list
    }
}