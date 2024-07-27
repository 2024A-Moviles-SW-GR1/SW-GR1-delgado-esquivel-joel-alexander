package com.epn.moviescrudapp

data class Movie(
    val id: Int,
    val title: String,
    val director: String,
    val genre: String,
    val year: Int,
    val synopsis: String,
    val actorId: Int
)

