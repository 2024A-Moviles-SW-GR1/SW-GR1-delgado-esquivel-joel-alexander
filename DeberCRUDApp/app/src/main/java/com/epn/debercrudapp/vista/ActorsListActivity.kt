package com.epn.debercrudapp.vista

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.epn.debercrudapp.R
import com.epn.debercrudapp.modeloVista.ActorViewModel

class ActorsListActivity : AppCompatActivity() {

    private val actorViewModel: ActorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actors_list)

        //Obtener referencias
        val actorsListView = findViewById<ListView>(R.id.actorsList)
        val actorsCreationButton = findViewById<Button>(R.id.actorsCreationButton)

        /*// Setup ArrayAdapter
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mutableListOf())
        actorsListView.adapter = adapter

        // Observe LiveData from ViewModel
        actorViewModel.actores.observe(this, { actores ->
            // Update the UI when the data changes
            actores?.let {
                val actorNames = it.map { actor -> "${actor.nombre} ${actor.apellido}" }
                adapter.clear()
                adapter.addAll(actorNames)
            }
        })

        // Load actors
        actorViewModel.cargarActores()*/

        // Setup button click listener
        actorsCreationButton.setOnClickListener {
            val intent = Intent(this, MoviesListActivity::class.java)
            startActivity(intent)
        }

    }
}