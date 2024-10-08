package com.epn.moviescrudapp

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActorsAdapter(private var actors: List<Actor>, private val listener: OnMovieClickListener) :
    RecyclerView.Adapter<ActorsAdapter.ActorViewHolder>() {

    //Interfaz para manejar los clics en las tarjetas de Actores
    interface OnMovieClickListener {
        fun onActorUpdateClick(actor: Actor)
        fun onActorDelete(actor: Actor)
        fun onMoviesView(actor: Actor)
    }

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val lastNameTextView: TextView = itemView.findViewById(R.id.lastNameTextView)
        val ageTextView: TextView = itemView.findViewById(R.id.ageTextView)
        val nationalityTextView: TextView = itemView.findViewById(R.id.nationalityTextView)
        val active: TextView = itemView.findViewById(R.id.activeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.actor_item, parent, false)
        return ActorViewHolder(view)
    }

    override fun getItemCount(): Int = actors.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actors[position]
        holder.nameTextView.text = "Nombre: ${actor.name}"
        holder.lastNameTextView.text = "Apellido: ${actor.lastName}"
        holder.ageTextView.text = "Edad: ${actor.age.toString()}"
        holder.nationalityTextView.text = "Nacionalidad: ${actor.nationality}"
        if (actor.active) {
            holder.active.text = "Estado: Activo"
        } else {
            holder.active.text = "Estado: Inactivo"
        }

        holder.itemView.setOnClickListener {
            val options = arrayOf("Ver películas", "Actualizar", "Eliminar")
            AlertDialog.Builder(it.context)
                .setTitle("Opciones")
                .setItems(options) { dialog, which ->
                    when (which) {
                        0 -> listener.onMoviesView(actor)
                        1 -> listener.onActorUpdateClick(actor)  // Actualizar
                        2 -> listener.onActorDelete(actor) // Eliminar
                    }
                }
                .show()
        }

    }

    //Para actualizar la lista de actores/actrices
    fun refreshActors(newActors: List<Actor>) {
        actors = newActors
        notifyDataSetChanged()
    }

}