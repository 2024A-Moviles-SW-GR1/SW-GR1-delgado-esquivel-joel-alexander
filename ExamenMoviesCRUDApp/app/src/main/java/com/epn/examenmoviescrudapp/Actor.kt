package com.epn.examenmoviescrudapp

data class Actor(
    val id: Int,
    val name: String,
    val lastName: String,
    val age: Int,
    val nationality: String,
    val active: Boolean,
    val latitud: Double,
    val longitud: Double
)
