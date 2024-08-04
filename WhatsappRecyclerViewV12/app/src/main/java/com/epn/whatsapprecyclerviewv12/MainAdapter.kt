package com.epn.whatsapprecyclerviewv12

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.CropCircleTransformation

class MainAdapter(private var chats: List<Chat>) : RecyclerView.Adapter<MainAdapter.ChatViewHolder>() {

class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val nameTextView: TextView = itemView.findViewById(R.id.textViewChatName)
    val lastMessageTextView: TextView = itemView.findViewById(R.id.textViewChatMessage)
    val timeTextView: TextView = itemView.findViewById(R.id.textViewChatTime)
    val imageViewProfile: ImageView = itemView.findViewById(R.id.imageViewProfile)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.ChatViewHolder, position: Int) {
        val chat = chats[position]

        holder.nameTextView.text = chat.name
        holder.lastMessageTextView.text = chat.lastMessage
        holder.timeTextView.text = chat.time

        Glide.with(holder.itemView.context)
            .load(chat.profilePicture)
            .apply(RequestOptions.bitmapTransform(CropCircleTransformation()))
            .into(holder.imageViewProfile)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ChatActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = chats.size


}