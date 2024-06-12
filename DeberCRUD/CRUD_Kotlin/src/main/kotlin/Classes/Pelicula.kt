package Classes

import java.io.File

class Pelicula(
    val id: Int,
    var nombre: String,
    var fechaLanzamiento: String,
    var duracion: Int,
    var presupuesto: Double
) {
    companion object {
        private const val archivo = "src/main/kotlin/Files/peliculas.txt"
        private val peliculas = mutableListOf<Pelicula>()

        init {
            if (File(archivo).exists()) {
                cargar()
            }
        }

        fun crear(id: Int, nombre: String, fechaLanzamiento: String, duracion: Int, presupuesto: Double) {
            val pelicula = Pelicula(id, nombre, fechaLanzamiento, duracion, presupuesto)
            peliculas.add(pelicula)
            guardar()
        }

        fun leer(): List<Pelicula> {
            return peliculas
        }

        fun actualizar(id: Int, nombre: String, fechaLanzamiento: String, duracion: Int, presupuesto: Double) {
            val pelicula = peliculas.find { it.id == id }
            if (pelicula != null) {
                pelicula.nombre = nombre
                pelicula.fechaLanzamiento = fechaLanzamiento
                pelicula.duracion = duracion
                pelicula.presupuesto = presupuesto
                guardar()
            }
        }

        fun eliminar(id: Int) {
            peliculas.removeIf { it.id == id }
            guardar()
        }

        private fun guardar() {
            File(archivo).writeText(peliculas.joinToString("\n") { "${it.id}|${it.nombre}|${it.fechaLanzamiento}|${it.duracion}|${it.presupuesto}" })
        }

        private fun cargar() {
            File(archivo).readLines().forEach {
                val datos = it.split("|")
                peliculas.add(Pelicula(datos[0].toInt(), datos[1], datos[2], datos[3].toInt(), datos[4].toDouble()))
            }
        }
    }
}
