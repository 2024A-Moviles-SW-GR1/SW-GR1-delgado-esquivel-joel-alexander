package com.epn.moviescrudapp

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(private var movies: List<Movie>, private val listener: OnMovieClickListener) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    //Interfaz para manejar los clics en las tarjetas de películas
    interface OnMovieClickListener {
        fun onMovieUpdateClick(movie: Movie)
        fun onMovieDelete(movie: Movie)
    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val directorTextView: TextView = itemView.findViewById(R.id.directorTextView)
        val genreTextView: TextView = itemView.findViewById(R.id.genreTextView)
        val yearTextView: TextView = itemView.findViewById(R.id.yearTextView)
        val synopsisTextView: TextView = itemView.findViewById(R.id.synopsisTextView)
        val movieEditButton: TextView = itemView.findViewById(R.id.movieEditButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleTextView.text = movie.title
        holder.directorTextView.text = "Director: ${movie.director}"
        holder.genreTextView.text = "Género: ${movie.genre}"
        holder.yearTextView.text = "Año de Estreno: ${movie.year.toString()}"
        holder.synopsisTextView.text = "Sinopsis: ${movie.synopsis}"

        holder.itemView.setOnClickListener {
            val options = arrayOf("Actualizar", "Eliminar")
            AlertDialog.Builder(it.context)
                .setTitle("Opciones")
                .setItems(options) { dialog, which ->
                    when (which) {
                        0 -> listener.onMovieUpdateClick(movie)  // Actualizar
                        1 -> listener.onMovieDelete(movie) // Eliminar
                    }
                }
                .show()
        }

    }

    //Para actualizar la lista de películas
    fun refreshMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }


}