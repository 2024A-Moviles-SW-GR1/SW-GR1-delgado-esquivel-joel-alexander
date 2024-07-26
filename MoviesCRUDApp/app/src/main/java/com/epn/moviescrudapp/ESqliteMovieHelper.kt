package com.epn.moviescrudapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteMovieHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        private const val DATABASE_NAME = "PeliculasYActores.db"
        private const val COLUMNA_ID = "id"
        private const val COLUMNA_TITULO = "titulo"
        private const val COLUMNA_DIRECTOR = "director"
        private const val COLUMNA_GENERO = "genero"
        private const val COLUMNA_ANIO = "anio"
        private const val COLUMNA_SINOPSIS = "sinopsis"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE MOVIE (${COLUMNA_ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${COLUMNA_TITULO} TEXT, ${COLUMNA_DIRECTOR} TEXT, ${COLUMNA_GENERO} TEXT, ${COLUMNA_ANIO} INTEGER, ${COLUMNA_SINOPSIS} TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS MOVIE"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun crearPelicula(
        movie: Movie
    ){
        val basedatosEscritura = writableDatabase
        val valorAGuardar = ContentValues().apply {
            put(COLUMNA_TITULO, movie.title)
            put(COLUMNA_DIRECTOR, movie.director)
            put(COLUMNA_GENERO, movie.genre)
            put(COLUMNA_ANIO, movie.year)
            put(COLUMNA_SINOPSIS, movie.synopsis)
        }
        val resultadoGuardar = basedatosEscritura.insert("MOVIE", null, valorAGuardar)
        basedatosEscritura.close()
    }

    fun consultarTodasLasPeliculas(): List<Movie> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM MOVIE"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)
        val listaPeliculas = mutableListOf<Movie>()
        val existeAlMenosUna = resultadoConsultaLectura.moveToFirst()

        if (existeAlMenosUna) {
            do {
                val pelicula = Movie(
                    resultadoConsultaLectura.getInt(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_ID)),
                    resultadoConsultaLectura.getString(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_TITULO)),
                    resultadoConsultaLectura.getString(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_DIRECTOR)),
                    resultadoConsultaLectura.getString(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_GENERO)),
                    resultadoConsultaLectura.getInt(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_ANIO)),
                    resultadoConsultaLectura.getString(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_SINOPSIS))
                )
                listaPeliculas.add(pelicula)
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaPeliculas
    }

}