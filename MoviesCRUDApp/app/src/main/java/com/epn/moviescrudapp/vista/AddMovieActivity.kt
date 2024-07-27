package com.epn.moviescrudapp.vista

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.epn.moviescrudapp.ESqliteMovieHelper
import com.epn.moviescrudapp.Movie
import com.epn.moviescrudapp.R

class AddMovieActivity : AppCompatActivity() {

    private lateinit var dbHelper: ESqliteMovieHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)


        dbHelper = ESqliteMovieHelper(this)
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

            // Se coloca el id de 0 porque se autoincrementa en la base de datos
            val movie = Movie(0, title, director, genre, year, synopsis)

            dbHelper.crearPelicula(movie)
            finish()
            Toast.makeText(this, "Película guardada", Toast.LENGTH_SHORT).show()
        }

    }
}