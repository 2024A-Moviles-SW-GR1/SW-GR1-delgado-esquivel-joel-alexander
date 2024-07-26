package com.epn.debercrudapp.vista

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.epn.debercrudapp.R
import com.epn.debercrudapp.modeloVista.MovieViewModel

class MoviesListActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        //Obtener referencias
        val moviesListView = findViewById<ListView>(R.id.moviesList)
        val moviesCreationButton = findViewById<Button>(R.id.moviesCreationButton)



        movieViewModel.cargarPeliculas()

        // Setup button click listener
        moviesCreationButton.setOnClickListener {
            val intent = Intent(this, MovieCreationActivity::class.java)
            startActivity(intent)
        }
    }
}