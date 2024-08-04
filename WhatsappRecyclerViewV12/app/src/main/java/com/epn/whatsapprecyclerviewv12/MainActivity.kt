package com.epn.whatsapprecyclerviewv12

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var chatsAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        chatsAdapter = MainAdapter(getSampleChats())
        val recyclerViewChats = findViewById<RecyclerView>(R.id.recyclerViewChats)
        recyclerViewChats.layoutManager = LinearLayoutManager(this)
        recyclerViewChats.adapter = chatsAdapter

    }

    fun getSampleChats(): List<Chat> {
        return listOf(
            Chat(
                name = "John Doe",
                lastMessage = "Hola, como estas?",
                time = "10:30 AM",
                profilePicture = "https://picsum.photos/200/300?random=1"
            ),
            Chat(
                name = "Jane Smith",
                lastMessage = "Vamos a vernos pronto!",
                time = "9:15 AM",
                profilePicture = "https://picsum.photos/200/300?random=2"
            ),
            Chat(
                name = "Mike Johnson",
                lastMessage = "No te olvides de la reunión de mañana.",
                time = "Yesterday",
                profilePicture = "https://picsum.photos/200/300?random=3"
            ),
            Chat(
                name = "Emily Davis",
                lastMessage = "Un gran trabajo con la presentación!",
                time = "2 days ago",
                profilePicture = "https://picsum.photos/200/300?random=4"
            ),
            Chat(
                name = "Ricardo Campana",
                lastMessage = "Puedes enviarme la tarea que te pedí?",
                time = "3 days ago",
                profilePicture = "https://picsum.photos/200/300?random=5"
            ),
            Chat(
                name = "David Wilson",
                lastMessage = "Puedes enviarme el reporte?",
                time = "3 days ago",
                profilePicture = "https://picsum.photos/200/300?random=5"
            ),
            Chat(
                name = "Sophia Brown",
                lastMessage = "Que tal te fue en el trabajo?",
                time = "2 hours ago",
                profilePicture = "https://picsum.photos/200/300?random=6"
            ),
            Chat(
                name = "Liam Johnson",
                lastMessage = "Alguna reunión programada para mañana?",
                time = "1 hour ago",
                profilePicture = "https://picsum.photos/200/300?random=7"
            ),
            Chat(
                name = "Emma Garcia",
                lastMessage = "Por supuesto, nos vemos ahí!",
                time = "10 minutes ago",
                profilePicture = "https://picsum.photos/200/300?random=8"
            ),
            Chat(
                name = "Noah Martinez",
                lastMessage = "Mañana será el cumpleaños de mi amiga!",
                time = "5 days ago",
                profilePicture = "https://picsum.photos/200/300?random=9"
            ),
            Chat(
                name = "Olivia Rodriguez",
                lastMessage = "Llamame cuando lo necesites",
                time = "2 days ago",
                profilePicture = "https://picsum.photos/200/300?random=10"
            ),
            Chat(
                name = "William Hernandez",
                lastMessage = "Gracias por la actualización",
                time = "4 hours ago",
                profilePicture = "https://picsum.photos/200/300?random=11"
            ),
            Chat(
                name = "Isabella Lopez",
                lastMessage = "Hablamos mañana!",
                time = "1 day ago",
                profilePicture = "https://picsum.photos/200/300?random=12"
            ),
            Chat(
                name = "James Gonzalez",
                lastMessage = "Quieres jugar mañana?",
                time = "2 minutes ago",
                profilePicture = "https://picsum.photos/200/300?random=13"
            ),
            Chat(
                name = "Ava Wilson",
                lastMessage = "Estamos a punto de terminar clases.",
                time = "6 hours ago",
                profilePicture = "https://picsum.photos/200/300?random=14"
            ),
            Chat(
                name = "Lucas Smith",
                lastMessage = "En camino!",
                time = "3 minutes ago",
                profilePicture = "https://picsum.photos/200/300?random=15"
            )
        )
    }


}