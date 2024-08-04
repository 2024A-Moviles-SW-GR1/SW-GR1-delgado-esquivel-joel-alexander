package com.epn.whatsapprecyclerviewv12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : AppCompatActivity() {

    lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        messageAdapter = MessageAdapter(generateSampleMessages())
        val recyclerViewChats = findViewById<RecyclerView>(R.id.recyclerViewMessages)
        recyclerViewChats.layoutManager = LinearLayoutManager(this)
        recyclerViewChats.adapter = messageAdapter

    }

    fun generateSampleMessages(): List<Mesage> {
        val messages = listOf(
            "¡Hola!",
            "¡Hola, qué tal!",
            "¿Cómo va todo?",
            "¡Buenos días!",
            "¡Gracias por tu ayuda!",
            "¡Nos vemos luego!",
            "¡Que tengas un gran día!",
            "Sí, estaré allí.",
            "No, no puedo asistir.",
            "Está bien, entiendo.",
            "Hola, quería saber cómo estás. Espero que todo esté yendo bien por tu parte.",
            "¡Hola! He estado pensando en nuestra última conversación y quería seguir con algunas nuevas ideas.",
            "¡Buenas tardes! Solo quería recordarte sobre la reunión que tenemos programada para mañana.",
            "Muchas gracias por tu apoyo durante el proyecto. Tus aportes fueron increíblemente útiles y apreciados.",
            "¡Hola! Espero que estés bien. Quería informarte sobre algunos cambios en el calendario para nuestro proyecto de equipo."
        )

        // Generate a list of 15 messages with varying lengths
        val sampleMessages = mutableListOf<Mesage>()
        for (i in 1..15) {
            val sender = "Sender $i"
            val content = if (i % 2 == 0) {
                // Pick a short message
                messages[(0..8).random()]
            } else {
                // Pick a long message
                messages[(9..14).random()]
            }
            sampleMessages.add(Mesage(sender, content))
        }

        return sampleMessages
    }



}