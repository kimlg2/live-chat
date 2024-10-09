package com.example.chaeting.Model

import com.example.chaeting.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class UserItem : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.chat_list
    }
}