package com.epn.debercrudapp.modelo

data class Actor(
    val id: Int = 0,
    val nombre: String,
    val apellido: String,
    val edad: Int,
    val nacionalidad: String,
    val peliculaId: Int
) {
    var peliculaTitulo: String? = null

    fun setPeliculaTitulo(titulo: String) {
        this.peliculaTitulo = titulo
    }
}

