package com.epn.debercrudapp.modeloVista

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.epn.debercrudapp.modelo.Actor
import com.epn.debercrudapp.modelo.ESqliteHelperActor


class ActorViewModel(application: Application) : AndroidViewModel(application) {
    private val dbHelper = ESqliteHelperActor(application)

    private val _actores = MutableLiveData<List<Actor>>()
    val actores: LiveData<List<Actor>> get() = _actores

    fun crearActor(nombre: String, apellido: String, edad: Int, nacionalidad: String, pelicula: String): Boolean {
        val resultado = dbHelper.crearActor(nombre, apellido, edad, nacionalidad, pelicula)
        if (resultado) {
            cargarActores()
        }
        return resultado
    }

    fun cargarActores() {
        _actores.value = dbHelper.consultarTodosLosActores()
    }
}
