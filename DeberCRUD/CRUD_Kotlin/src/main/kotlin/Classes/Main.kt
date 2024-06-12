package Classes
import java.io.File

fun main() {
    while (true) {
        println("Menú Principal")
        println("1. Crear Película")
        println("2. Crear Actor")
        println("3. Leer Películas")
        println("4. Leer Actores")
        println("5. Actualizar Película")
        println("6. Actualizar Actor")
        println("7. Eliminar Película")
        println("8. Eliminar Actor")
        println("9. Salir")

        when (readLine()!!.toInt()) {
            1 -> crearPelicula()
            2 -> crearActor()
            3 -> leerPeliculas()
            4 -> leerActores()
            5 -> actualizarPelicula()
            6 -> actualizarActor()
            7 -> eliminarPelicula()
            8 -> eliminarActor()
            9 -> break
            else -> println("Opción no válida")
        }
    }
}

fun crearPelicula() {
    println("Ingrese ID de la película:")
    val id = readLine()!!.toInt()
    println("Ingrese nombre de la película:")
    val nombre = readLine()!!
    println("Ingrese fecha de lanzamiento (yyyy-MM-dd):")
    val fechaLanzamiento = readLine()!!
    println("Ingrese duración (minutos):")
    val duracion = readLine()!!.toInt()
    println("Ingrese presupuesto:")
    val presupuesto = readLine()!!.toDouble()

    Pelicula.crear(id, nombre, fechaLanzamiento, duracion, presupuesto)
    println("Película creada exitosamente.")
}

fun crearActor() {
    println("Ingrese ID del actor:")
    val id = readLine()!!.toInt()
    println("Ingrese nombre del actor:")
    val nombre = readLine()!!
    println("Ingrese edad del actor:")
    val edad = readLine()!!.toInt()
    println("Ingrese nacionalidad del actor:")
    val nacionalidad = readLine()!!
    println("Es el actor principal? (true/false):")
    val esPrincipal = readLine()!!.toBoolean()
    println("Ingrese ID de la película a la que pertenece:")
    val idPelicula = readLine()!!.toInt()

    val peliculas = Pelicula.leer()
    if (peliculas.any { it.id == idPelicula }) {
        Actor.crear(id, nombre, edad, nacionalidad, esPrincipal, idPelicula)
        println("Actor creado exitosamente.")
    } else {
        println("La película con ID $idPelicula no existe.")
    }
}


fun leerPeliculas() {
    println("Listado de Películas:")
    Pelicula.leer().forEach {
        println("ID: ${it.id}, Nombre: ${it.nombre}, Fecha de Lanzamiento: ${it.fechaLanzamiento}, Duración: ${it.duracion} min, Presupuesto: $${it.presupuesto}")
    }
    Thread.sleep(10000)
}

fun leerActores() {
    println("Listado de Actores:")
    val peliculas = Pelicula.leer()
    Actor.leer().forEach {
        val pelicula = peliculas.find { p -> p.id == it.idPelicula }
        println("ID: ${it.id}, Nombre: ${it.nombre}, Edad: ${it.edad}, Nacionalidad: ${it.nacionalidad}, Principal: ${it.esPrincipal}, Película: ${pelicula?.nombre ?: "No encontrada"}")
    }
    Thread.sleep(10000)
}


fun actualizarPelicula() {
    println("Ingrese ID de la película a actualizar:")
    val id = readLine()!!.toInt()
    val peliculas = Pelicula.leer()
    val pelicula = peliculas.find { it.id == id }
    if (pelicula != null) {
        println("Ingrese nuevo nombre de la película (actual: ${pelicula.nombre}):")
        val nombre = readLine()!!
        println("Ingrese nueva fecha de lanzamiento (actual: ${pelicula.fechaLanzamiento}):")
        val fechaLanzamiento = readLine()!!
        println("Ingrese nueva duración (actual: ${pelicula.duracion}):")
        val duracion = readLine()!!.toInt()
        println("Ingrese nuevo presupuesto (actual: ${pelicula.presupuesto}):")
        val presupuesto = readLine()!!.toDouble()
        Pelicula.actualizar(id, nombre, fechaLanzamiento, duracion, presupuesto)
        println("Película actualizada exitosamente.")
    } else {
        println("La película con ID $id no existe.")
    }
}

fun actualizarActor() {
    println("Ingrese ID del actor a actualizar:")
    val id = readLine()!!.toInt()
    val actores = Actor.leer()
    val actor = actores.find { it.id == id }
    if (actor != null) {
        println("Ingrese nuevo nombre del actor (actual: ${actor.nombre}):")
        val nombre = readLine()!!
        println("Ingrese nueva edad del actor (actual: ${actor.edad}):")
        val edad = readLine()!!.toInt()
        println("Ingrese nueva nacionalidad del actor (actual: ${actor.nacionalidad}):")
        val nacionalidad = readLine()!!
        println("Es el actor principal? (actual: ${actor.esPrincipal}, true/false):")
        val esPrincipal = readLine()!!.toBoolean()
        println("Ingrese nuevo ID de la película a la que pertenece (actual: ${actor.idPelicula}):")
        val idPelicula = readLine()!!.toInt()

        val peliculas = Pelicula.leer()
        if (peliculas.any { it.id == idPelicula }) {
            Actor.actualizar(id, nombre, edad, nacionalidad, esPrincipal, idPelicula)
            println("Actor actualizado exitosamente.")
        } else {
            println("La película con ID $idPelicula no existe.")
        }
    } else {
        println("El actor con ID $id no existe.")
    }
}


fun eliminarPelicula() {
    println("Ingrese ID de la película a eliminar:")
    val id = readLine()!!.toInt()
    val actores = Actor.leer()
    if (actores.any { it.idPelicula == id }) {
        println("No se puede eliminar la película porque tiene actores asociados.")
    } else {
        Pelicula.eliminar(id)
        println("Película eliminada exitosamente.")
    }
}

fun eliminarActor() {
    println("Ingrese ID del actor a eliminar:")
    val id = readLine()!!.toInt()
    Actor.eliminar(id)
    println("Actor eliminado exitosamente.")
}
