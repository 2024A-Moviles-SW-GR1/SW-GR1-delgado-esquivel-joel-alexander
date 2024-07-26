package com.epn.debercrudapp.vista

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.epn.debercrudapp.R
import com.epn.debercrudapp.modeloVista.MovieViewModel

class MovieCreationActivity : AppCompatActivity() {
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_creation)

        val titleEditText: EditText = findViewById(R.id.editTextTitle)
        val directorEditText: EditText = findViewById(R.id.editTextDirector)
        val genreEditText: EditText = findViewById(R.id.editTextGenre)
        val yearEditText: EditText = findViewById(R.id.editTextYear)
        val synopsisEditText: EditText = findViewById(R.id.editTextSynopsis)
        val button: Button = findViewById(R.id.movieCreationButton)

        button.setOnClickListener {
            val title = titleEditText.text.toString()
            val director = directorEditText.text.toString()
            val genre = genreEditText.text.toString()
            val year = yearEditText.text.toString().toIntOrNull() ?: 0
            val synopsis = synopsisEditText.text.toString()

            movieViewModel.añadirPelicula(title, director, genre, year, synopsis)
            finish()
        }
    }
}
