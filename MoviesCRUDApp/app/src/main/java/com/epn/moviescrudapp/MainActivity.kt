package com.epn.moviescrudapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), MoviesAdapter.OnMovieClickListener {

    private lateinit var dbHelper: ESqliteMovieHelper
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val movieCreationButton = findViewById<Button>(R.id.movieCreationButton)

        dbHelper = ESqliteMovieHelper(this)
        moviesAdapter = MoviesAdapter(dbHelper.consultarTodasLasPeliculas(), this)

        val movieListView = findViewById<RecyclerView>(R.id.movieRecyclerView)
        movieListView.layoutManager = LinearLayoutManager(this)
        movieListView.adapter = moviesAdapter

        movieCreationButton.setOnClickListener {
            val intent = Intent(this, AddMovieActivity::class.java)
            startActivity(intent)
        }

    }


//    override fun onMovieUpdateClick(movie: Movie) {
//        // Aquí puedes iniciar la actividad de actualización pasando la información de la película
//        val intent = Intent(this, UpdateMovieActivity::class.java)
//        intent.putExtra("MOVIE_ID", movie.id)
//        startActivity(intent)
//    }
//
//    override fun onMovieDelete(movie: Movie) {
//        // Aquí puedes eliminar la película de la base de datos
//        dbHelper.eliminarPelicula(movie.id)
//        moviesAdapter.refreshMovies(dbHelper.consultarTodasLasPeliculas())
//    }

    override fun onResume() {
        super.onResume()
        moviesAdapter.refreshMovies(dbHelper.consultarTodasLasPeliculas())
    }

    override fun onMovieUpdateClick(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun onMovieDelete(movie: Movie) {
        TODO("Not yet implemented")
    }

}