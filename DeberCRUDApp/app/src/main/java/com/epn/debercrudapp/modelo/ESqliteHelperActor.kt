package com.epn.debercrudapp.modelo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperActor(contexto: Context) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaActor =
            """
                CREATE TABLE ACTOR(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    apellido VARCHAR(50),
                    edad INTEGER,
                    nacionalidad VARCHAR(50),
                    peliculaId INTEGER REFERENCES MOVIE(id)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaActor)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun crearActor(
        nombre: String,
        apellido: String,
        edad: Int,
        nacionalidad: String,
        peliculaId: Int
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues().apply {
            put("nombre", nombre)
            put("apellido", apellido)
            put("edad", edad)
            put("nacionalidad", nacionalidad)
            put("peliculaId", peliculaId)
        }
        val resultadoGuardar = basedatosEscritura.insert("ACTOR", null, valoresAGuardar)
        basedatosEscritura.close()
        return resultadoGuardar.toInt() != -1
    }

    fun eliminarActor(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultado = conexionEscritura.delete("ACTOR", "id = ?", parametrosConsultaDelete)
        conexionEscritura.close()
        return resultado != -1
    }

    fun actualizarActor(
        id: Int,
        nombre: String,
        apellido: String,
        edad: Int,
        nacionalidad: String,
        peliculaId: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues().apply {
            put("nombre", nombre)
            put("apellido", apellido)
            put("edad", edad)
            put("nacionalidad", nacionalidad)
            put("peliculaId", peliculaId)
        }
        val parametrosActualizar = arrayOf(id.toString())
        val resultado =
            conexionEscritura.update("ACTOR", valoresActualizar, "id = ?", parametrosActualizar)
        conexionEscritura.close()
        return resultado != -1
    }

    fun consultarTodosLosActores(): List<Actor> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
                        SELECT ACTOR.id, ACTOR.nombre, ACTOR.apellido, ACTOR.edad, ACTOR.nacionalidad, MOVIE.id, MOVIE.titulo
                        FROM ACTOR
                        INNER JOIN MOVIE ON ACTOR.pelicula = MOVIE.id
        """.trimIndent()
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)
        val listaActores = mutableListOf<Actor>()
        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val actor = Actor(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getInt(3),
                    resultadoConsultaLectura.getString(4),
                    resultadoConsultaLectura.getInt(5)
                ).apply {
                    setPeliculaTitulo(resultadoConsultaLectura.getString(6))
                }
                listaActores.add(actor)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaActores
    }
}
