package com.epn.debercrudapp.vista

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.epn.debercrudapp.R
import com.epn.debercrudapp.modelo.ESqliteHelperActor
import com.epn.debercrudapp.modeloVista.ActorViewModel

class ActorCreationActivity : AppCompatActivity() {

    private val actorViewModel: ActorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actorCreationButton = findViewById<Button>(R.id.actorCreationButton)
        val editTextActorName = findViewById<EditText>(R.id.editTextActorName)
        val editTextActorSurname = findViewById<EditText>(R.id.editTextActorSurname)
        val editTextActorAge = findViewById<EditText>(R.id.editTextActorAge)
        val editTextActorNationality = findViewById<EditText>(R.id.editTextActorNationality)
        val spinnerMovies = findViewById<Spinner>(R.id.spinnerMovies)

        enableEdgeToEdge()
        setContentView(R.layout.activity_actor_creation)

    }
}