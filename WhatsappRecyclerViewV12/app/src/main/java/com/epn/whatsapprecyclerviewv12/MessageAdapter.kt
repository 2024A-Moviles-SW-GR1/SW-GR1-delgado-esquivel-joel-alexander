package com.epn.whatsapprecyclerviewv12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.epn.whatsapprecyclerviewv12.MainAdapter.ChatViewHolder

class MessageAdapter(private var messages: List<Mesage>): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    class MessageViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val senderTextView: TextView = itemView.findViewById(R.id.textViewMessageSent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_sent, parent, false)
        return MessageViewHolder(view)
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]

        holder.senderTextView.text = message.content
    }

}