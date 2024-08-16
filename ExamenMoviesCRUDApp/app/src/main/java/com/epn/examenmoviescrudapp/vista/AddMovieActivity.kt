package com.epn.examenmoviescrudapp.vista

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.epn.examenmoviescrudapp.ESqliteMovieHelper
import com.epn.examenmoviescrudapp.Movie
import com.epn.examenmoviescrudapp.R

class AddMovieActivity : AppCompatActivity() {

    private lateinit var dbHelper: ESqliteMovieHelper
    private var actorId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)


        dbHelper = ESqliteMovieHelper(this)
        val titleEditText: EditText = findViewById(R.id.editTextTitle)
        val directorEditText: EditText = findViewById(R.id.editTextDirector)
        val genreEditText: EditText = findViewById(R.id.editTextGenre)
        val yearEditText: EditText = findViewById(R.id.editTextYear)
        val synopsisEditText: EditText = findViewById(R.id.editTextSynopsis)
        val movieCreationButton: Button = findViewById(R.id.movieCreationButton)

        //Se obtiene el id desde la actividad anterior
        actorId = intent.getIntExtra("actor_id", -1)
        if (actorId == -1) {
            finish()
            return
        }

        movieCreationButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val director = directorEditText.text.toString()
            val genre = genreEditText.text.toString()
            val year = yearEditText.text.toString().toIntOrNull() ?: 0
            val synopsis = synopsisEditText.text.toString()

            // Se coloca el id de 0 porque se autoincrementa en la base de datos
            val movie = Movie(0, title, director, genre, year, synopsis, actorId)

            dbHelper.crearPelicula(movie)
            finish()
            Toast.makeText(this, "Película guardada", Toast.LENGTH_SHORT).show()
        }

    }
}