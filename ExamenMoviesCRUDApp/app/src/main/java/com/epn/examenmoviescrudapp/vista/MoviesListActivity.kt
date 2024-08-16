package com.epn.examenmoviescrudapp.vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epn.examenmoviescrudapp.ESqliteMovieHelper
import com.epn.examenmoviescrudapp.Movie
import com.epn.examenmoviescrudapp.MoviesAdapter
import com.epn.examenmoviescrudapp.R

class MoviesListActivity : AppCompatActivity(), MoviesAdapter.OnMovieClickListener {

    private lateinit var dbHelper: ESqliteMovieHelper
    private var actorId: Int = -1
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
        val movieCreationButton = findViewById<Button>(R.id.movieCreationButton)

        dbHelper = ESqliteMovieHelper(this)

        //Se obtiene el id desde la actividad anterior
        actorId = intent.getIntExtra("actor_id", -1)
        if (actorId == -1) {
            finish()
            return
        }


        moviesAdapter = MoviesAdapter(dbHelper.consultarLasPeliculasPorActor(actorId), this)

        val movieListView = findViewById<RecyclerView>(R.id.movieRecyclerView)
        movieListView.layoutManager = LinearLayoutManager(this)
        movieListView.adapter = moviesAdapter

        movieCreationButton.setOnClickListener {
            val intent = Intent(this, AddMovieActivity::class.java)
            intent.putExtra("actor_id", actorId)
            startActivity(intent)
        }

    }

    override fun onMovieUpdateClick(movie: Movie) {
        val intent = Intent(this, MovieUpdateActivity::class.java)
        intent.putExtra("movie_id", movie.id)
        startActivity(intent)
    }

    override fun onMovieDelete(movie: Movie) {
        // Aquí puedes eliminar la película de la base de datos
        dbHelper.eliminarPelicula(movie.id)
        moviesAdapter.refreshMovies(dbHelper.consultarLasPeliculasPorActor(actorId))
    }

    override fun onResume() {
        super.onResume()
        moviesAdapter.refreshMovies(dbHelper.consultarLasPeliculasPorActor(actorId))
    }

}