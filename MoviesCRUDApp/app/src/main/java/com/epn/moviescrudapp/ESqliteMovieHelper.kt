package com.epn.moviescrudapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteMovieHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        private const val DATABASE_NAME = "PeliculasYActores.db"
        private const val COLUMNA_ID = "id"
        private const val COLUMNA_TITULO = "titulo"
        private const val COLUMNA_DIRECTOR = "director"
        private const val COLUMNA_GENERO = "genero"
        private const val COLUMNA_ANIO = "anio"
        private const val COLUMNA_SINOPSIS = "sinopsis"
        private const val COLUMNA_ID_ACTOR = "id_actor"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //No funciona crear desde aquí
        val createTableQuery =
            "CREATE TABLE MOVIE (${COLUMNA_ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${COLUMNA_TITULO} TEXT, ${COLUMNA_DIRECTOR} TEXT, ${COLUMNA_GENERO} TEXT, ${COLUMNA_ANIO} INTEGER, ${COLUMNA_SINOPSIS} TEXT, ${COLUMNA_ID_ACTOR} INTEGER, FOREIGN KEY(${COLUMNA_ID_ACTOR}) REFERENCES ACTOR(id) ON DELETE CASCADE)"
        db?.execSQL(createTableQuery)
        Log.d("Database", "Table MOVIE created")
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
            put(COLUMNA_ID_ACTOR, movie.actorId)
        }
        val resultadoGuardar = basedatosEscritura.insert("MOVIE", null, valorAGuardar)
        basedatosEscritura.close()
    }

    fun consultarLasPeliculasPorActor(actorId: Int): List<Movie> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM MOVIE WHERE $COLUMNA_ID_ACTOR = ?"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, arrayOf(actorId.toString()))
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
                    resultadoConsultaLectura.getString(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_SINOPSIS)),
                    resultadoConsultaLectura.getInt(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_ID_ACTOR))
                )
                listaPeliculas.add(pelicula)
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaPeliculas
    }

    fun consultarPeliculaPorId(id: Int): Movie? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM MOVIE WHERE ${COLUMNA_ID} = $id"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)
        resultadoConsultaLectura.moveToFirst()

        val id = resultadoConsultaLectura.getInt(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_ID))
        val title = resultadoConsultaLectura.getString(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_TITULO))
        val director = resultadoConsultaLectura.getString(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_DIRECTOR))
        val genre = resultadoConsultaLectura.getString(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_GENERO))
        val year = resultadoConsultaLectura.getInt(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_ANIO))
        val synopsis = resultadoConsultaLectura.getString(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_SINOPSIS))
        val id_actor = resultadoConsultaLectura.getInt(resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_ID_ACTOR))

        baseDatosLectura.close()
        resultadoConsultaLectura.close()
        return Movie(id, title, director, genre, year, synopsis, id_actor)
    }

    fun actualizarPelicula(
        movie: Movie
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues().apply {
            put(COLUMNA_TITULO, movie.title)
            put(COLUMNA_DIRECTOR, movie.director)
            put(COLUMNA_GENERO, movie.genre)
            put(COLUMNA_ANIO, movie.year)
            put(COLUMNA_SINOPSIS, movie.synopsis)
        }
        val parametrosActualizar = arrayOf(movie.id.toString())
        val resultado =
            conexionEscritura.update("MOVIE", valoresActualizar, "id = ?", parametrosActualizar)
        conexionEscritura.close()
        return resultado != -1
    }

    fun eliminarPelicula(id: Int){
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultado = conexionEscritura.delete("MOVIE", "$COLUMNA_ID = ?", parametrosConsultaDelete)
        conexionEscritura.close()
    }

}