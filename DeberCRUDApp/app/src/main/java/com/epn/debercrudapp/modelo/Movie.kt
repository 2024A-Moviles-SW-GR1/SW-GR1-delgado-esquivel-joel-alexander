package com.epn.debercrudapp.modelo

data class Movie (
    var id: Int = 0,
    var titulo: String,
    var director: String,
    var anio: Int,
    var genero: String
)
