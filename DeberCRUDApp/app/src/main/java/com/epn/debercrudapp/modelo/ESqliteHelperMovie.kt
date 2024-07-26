package com.epn.debercrudapp.modelo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperMovie(context: Context) : SQLiteOpenHelper(context, "moviles", null, 1) {

    private const val COLUMNA_ID = "id"
    private const val COLUMNA_TITULO = "titulo"
    private const val COLUMNA_DIRECTOR = "director"
    private const val COLUMNA_GENERO = "genero"
    private const val COLUMNA_ANIO = "anio"
    private const val COLUMNA_SINOPSIS = "sinopsis"

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaMovie = """
            CREATE TABLE MOVIE(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            titulo VARCHAR(50),
            director VARCHAR(50),
            anio INTEGER,
            genero VARCHAR(50)
            )
        """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaMovie)

        //Insertar película inicial
        val scriptInsertarPelicula = """
            INSERT INTO MOVIE (titulo, director, anio, genero) VALUES ('Venom', 'Samuel Roosevelt', 2018, 'Acción')
        """.trimIndent()

        db?.execSQL(scriptInsertarPelicula)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun crearPelicula(
        titulo: String,
        director: String,
        genero: String,
        año: Int,
        sinopsis: String
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valorAGuardar = ContentValues().apply {
            put("titulo", titulo)
            put("director", director)
            put("genero", genero)
            put("anio", año)
            put("sinopsis", sinopsis)
        }
        val resultadoGuardar = basedatosEscritura.insert("MOVIE", null, valorAGuardar)
        basedatosEscritura.close()
        return resultadoGuardar.toInt() != -1
    }

    fun eliminarPelicula(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultado = conexionEscritura.delete("MOVIE", "id = ?", parametrosConsultaDelete)
        conexionEscritura.close()
        return resultado != -1
    }

    fun actualizarPelicula(
        id: Int,
        titulo: String,
        director: String,
        genero: String,
        año: Int,
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues().apply {
            put("titulo", titulo)
            put("director", director)
            put("genero", genero)
            put("anio", año)
        }
        val parametrosActualizar = arrayOf(id.toString())
        val resultado =
            conexionEscritura.update("MOVIE", valoresActualizar, "id = ?", parametrosActualizar)
        conexionEscritura.close()
        return resultado != -1
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
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getInt(3),
                    resultadoConsultaLectura.getString(4)
                )
                listaPeliculas.add(pelicula)
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaPeliculas
    }

}

