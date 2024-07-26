package com.epn.moviescrudapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MovieUpdateActivity : AppCompatActivity() {

    private lateinit var dbHelper: ESqliteMovieHelper
    private var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_update)

        dbHelper = ESqliteMovieHelper(this)
        val movieEditButton: Button = findViewById(R.id.movieEditButton)
        val editTextTitle: EditText = findViewById(R.id.editTextTitle)
        val editTextDirector: EditText = findViewById(R.id.editTextDirector)
        val editTextGenre: EditText = findViewById(R.id.editTextGenre)
        val editTextYear: EditText = findViewById(R.id.editTextYear)
        val editTextSynopsis: EditText = findViewById(R.id.editTextSynopsis)

        movieId = intent.getIntExtra("movie_id", -1)
        if (movieId == -1) {
            finish()
            return
        }

        val movie = dbHelper.consultarPeliculaPorId(movieId)
        editTextTitle.setText(movie?.title)
        editTextDirector.setText(movie?.director)
        editTextGenre.setText(movie?.genre)
        editTextYear.setText(movie?.year.toString())
        editTextSynopsis.setText(movie?.synopsis)

        movieEditButton.setOnClickListener {
            val newTitle = editTextTitle.text.toString()
            val newDirector = editTextDirector.text.toString()
            val newGenre = editTextGenre.text.toString()
            val newYear = editTextYear.text.toString().toIntOrNull() ?: 0
            val newSynopsis = editTextSynopsis.text.toString()

            val updatedMovie = Movie(movieId, newTitle, newDirector, newGenre, newYear, newSynopsis)
            dbHelper.actualizarPelicula(updatedMovie)
            finish()
            Toast.makeText(this, "Película actualizada correctamente", Toast.LENGTH_SHORT).show()
        }

    }
}