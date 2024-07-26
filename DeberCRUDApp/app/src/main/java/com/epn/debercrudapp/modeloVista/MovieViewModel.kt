package com.epn.debercrudapp.modeloVista

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.epn.debercrudapp.modelo.ESqliteHelperMovie
import com.epn.debercrudapp.modelo.Movie


class MovieViewModel(application: Application): AndroidViewModel(application) {

    private val dbHelper = ESqliteHelperMovie(application)
    val movieList = MutableLiveData<List<Movie>>()

    fun crearPelicula(titulo: String, director: String, genero: String, año: Int, sinopsis: String): Boolean {
        val resultado = dbHelper.crearPelicula(titulo, director, genero, año, sinopsis)
        if (resultado) {
            cargarPeliculas()
        }
        return resultado
    }

    fun cargarPeliculas() {
        movieList.value = dbHelper.consultarTodasLasPeliculas()
    }

    fun añadirPelicula(titulo: String, director: String, genero: String, año: Int, sinopsis: String) {
        dbHelper.crearPelicula(titulo, director, genero, año, sinopsis)
        cargarPeliculas()
    }

    fun actualizarPelicula(id: Int, titulo: String, director: String, genero: String, año: Int) {
        dbHelper.actualizarPelicula(id, titulo, director, genero, año)
        cargarPeliculas()
    }

    fun borrarPelicula(id: Int) {
        dbHelper.eliminarPelicula(id)
        cargarPeliculas()
    }

}
