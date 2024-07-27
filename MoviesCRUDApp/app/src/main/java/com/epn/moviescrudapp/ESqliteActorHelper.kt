package com.epn.moviescrudapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteActorHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        private const val DATABASE_NAME = "PeliculasYActores.db"
        private const val NOMBRE_TABLA_ACTOR = "ACTOR"
        private const val NOMBRE_TABLA_MOVIE = "MOVIE"

        private const val COLUMNA_ACTOR_ID = "id"
        private const val COLUMNA_NOMBRE = "nombre"
        private const val COLUMNA_APELLIDO = "apellido"
        private const val COLUMNA_EDAD = "edad"
        private const val COLUMNA_NACIONALIDAD = "nacionalidad"
        private const val COLUMNA_ACTIVO = "activo"

        private const val COLUMNA_MOVIE_ID = "id"
        private const val COLUMNA_TITULO = "titulo"
        private const val COLUMNA_DIRECTOR = "director"
        private const val COLUMNA_GENERO = "genero"
        private const val COLUMNA_ANIO = "anio"
        private const val COLUMNA_SINOPSIS = "sinopsis"
        private const val COLUMNA_ID_ACTOR = "id_actor"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        //Crear la tabla ACTOR
        val createTableQuery =
            "CREATE TABLE $NOMBRE_TABLA_ACTOR (${COLUMNA_ACTOR_ID} INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMNA_NOMBRE TEXT, $COLUMNA_APELLIDO TEXT, $COLUMNA_EDAD INTEGER, $COLUMNA_NACIONALIDAD TEXT, $COLUMNA_ACTIVO BOOLEAN)"
        db?.execSQL(createTableQuery)
        Log.d("Database", "Table ACTOR created")

        //Crear la tabla MOVIE
        val createTableQuery2 =
            "CREATE TABLE MOVIE ($COLUMNA_MOVIE_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMNA_TITULO TEXT, $COLUMNA_DIRECTOR TEXT, $COLUMNA_GENERO TEXT, $COLUMNA_ANIO INTEGER, $COLUMNA_SINOPSIS TEXT, $COLUMNA_ID_ACTOR INTEGER, FOREIGN KEY($COLUMNA_ID_ACTOR) REFERENCES ACTOR(id) ON DELETE CASCADE)"
        db?.execSQL(createTableQuery2)
        Log.d("Database", "Table MOVIE created")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $NOMBRE_TABLA_ACTOR"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun crearActor(
        actor: Actor
    ) {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues().apply {
            put(COLUMNA_NOMBRE, actor.name)
            put(COLUMNA_APELLIDO, actor.lastName)
            put(COLUMNA_EDAD, actor.age)
            put(COLUMNA_NACIONALIDAD, actor.nationality)
            put(COLUMNA_ACTIVO, actor.active)
        }
        val resultadoGuardar = basedatosEscritura.insert("ACTOR", null, valoresAGuardar)
        basedatosEscritura.close()
    }

    fun consultarTodosLosActores(): List<Actor> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM $NOMBRE_TABLA_ACTOR"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)
        val listaActores = mutableListOf<Actor>()
        val existeAlMenosUna = resultadoConsultaLectura.moveToFirst()

        if (existeAlMenosUna) {
            do {
                val actor = Actor(
                    resultadoConsultaLectura.getInt(
                        resultadoConsultaLectura.getColumnIndexOrThrow(
                            COLUMNA_ACTOR_ID
                        )
                    ),
                    resultadoConsultaLectura.getString(
                        resultadoConsultaLectura.getColumnIndexOrThrow(
                            COLUMNA_NOMBRE
                        )
                    ),
                    resultadoConsultaLectura.getString(
                        resultadoConsultaLectura.getColumnIndexOrThrow(
                            COLUMNA_APELLIDO
                        )
                    ),
                    resultadoConsultaLectura.getInt(
                        resultadoConsultaLectura.getColumnIndexOrThrow(
                            COLUMNA_EDAD
                        )
                    ),
                    resultadoConsultaLectura.getString(
                        resultadoConsultaLectura.getColumnIndexOrThrow(
                            COLUMNA_NACIONALIDAD
                        )
                    ),
                    resultadoConsultaLectura.getInt(
                        resultadoConsultaLectura.getColumnIndexOrThrow(
                            COLUMNA_ACTIVO
                        )
                    ) == 1
                )
                listaActores.add(actor)
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaActores
    }

    fun consultarActorPorId(id: Int): Actor? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM $NOMBRE_TABLA_ACTOR WHERE ${COLUMNA_ACTOR_ID} = $id"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)
        resultadoConsultaLectura.moveToFirst()

        val id = resultadoConsultaLectura.getInt(
            resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_ACTOR_ID)
        )
        val name = resultadoConsultaLectura.getString(
            resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_NOMBRE)
        )
        val lastName = resultadoConsultaLectura.getString(
            resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_APELLIDO)
        )
        val age = resultadoConsultaLectura.getInt(
            resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_EDAD)
        )
        val nationality = resultadoConsultaLectura.getString(
            resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_NACIONALIDAD)
        )
        val active = resultadoConsultaLectura.getInt(
            resultadoConsultaLectura.getColumnIndexOrThrow(COLUMNA_ACTIVO)
        ) == 1

        baseDatosLectura.close()
        resultadoConsultaLectura.close()
        return Actor(id, name, lastName, age, nationality, active)
    }

    fun actualizarActor(actor: Actor): Boolean {
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues().apply {
            put(COLUMNA_NOMBRE, actor.name)
            put(COLUMNA_APELLIDO, actor.lastName)
            put(COLUMNA_EDAD, actor.age)
            put(COLUMNA_NACIONALIDAD, actor.nationality)
            put(COLUMNA_ACTIVO, actor.active)
        }
        val parametrosActualizar = arrayOf(actor.id.toString())
        val resultado =
            conexionEscritura.update(NOMBRE_TABLA_ACTOR, valoresActualizar, "id = ?", parametrosActualizar)
        conexionEscritura.close()
        return resultado != -1
    }

    fun eliminarActor(id: Int){
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultado = conexionEscritura.delete(NOMBRE_TABLA_ACTOR, "$COLUMNA_ACTOR_ID = ?", parametrosConsultaDelete)
        conexionEscritura.close()
    }

}