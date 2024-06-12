package Classes

import java.io.File

class Actor(
    val id: Int,
    var nombre: String,
    var edad: Int,
    var nacionalidad: String,
    var esPrincipal: Boolean,
    var idPelicula: Int
) {
    companion object {
        private const val archivo = "src/main/kotlin/Files/actores.txt"
        private val actores = mutableListOf<Actor>()

        init {
            if (File(archivo).exists()) {
                cargar()
            }
        }

        fun crear(id: Int, nombre: String, edad: Int, nacionalidad: String, esPrincipal: Boolean, idPelicula: Int) {
            val actor = Actor(id, nombre, edad, nacionalidad, esPrincipal, idPelicula)
            actores.add(actor)
            guardar()
        }

        fun leer(): List<Actor> {
            return actores
        }

        fun actualizar(id: Int, nombre: String, edad: Int, nacionalidad: String, esPrincipal: Boolean, idPelicula: Int) {
            val actor = actores.find { it.id == id }
            if (actor != null) {
                actor.nombre = nombre
                actor.edad = edad
                actor.nacionalidad = nacionalidad
                actor.esPrincipal = esPrincipal
                actor.idPelicula = idPelicula
                guardar()
            }
        }

        fun eliminar(id: Int) {
            actores.removeIf { it.id == id }
            guardar()
        }

        private fun guardar() {
            File(archivo).writeText(actores.joinToString("\n") { "${it.id}|${it.nombre}|${it.edad}|${it.nacionalidad}|${it.esPrincipal}|${it.idPelicula}" })
        }

        private fun cargar() {
            File(archivo).readLines().forEach {
                val datos = it.split("|")
                actores.add(Actor(datos[0].toInt(), datos[1], datos[2].toInt(), datos[3], datos[4].toBoolean(), datos[5].toInt()))
            }
        }
    }
}
