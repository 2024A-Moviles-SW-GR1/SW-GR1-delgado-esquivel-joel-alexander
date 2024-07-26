package com.epn.moviescrudapp

data class Movie(
    val id: Int,
    val title: String,
    val director: String,
    val genre: String,
    val year: Int,
    val synopsis: String
) {
    override fun toString(): String {
        return "Movie(id=$id, title='$title', director='$director', genre='$genre', year=$year, synopsis='$synopsis')"
    }
}

