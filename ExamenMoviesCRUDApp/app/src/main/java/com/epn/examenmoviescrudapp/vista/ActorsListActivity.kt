package com.epn.examenmoviescrudapp.vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epn.examenmoviescrudapp.Actor
import com.epn.examenmoviescrudapp.ActorsAdapter
import com.epn.examenmoviescrudapp.ESqliteActorHelper
import com.epn.examenmoviescrudapp.R

class ActorsListActivity : AppCompatActivity(), ActorsAdapter.OnMovieClickListener {

    private lateinit var dbHelper: ESqliteActorHelper
    private lateinit var actorsAdapter: ActorsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors_list)
        val actorsCreationButton = findViewById<Button>(R.id.actorsCreationButton)

        dbHelper = ESqliteActorHelper(this)
        actorsAdapter = ActorsAdapter(dbHelper.consultarTodosLosActores(), this)

        val actorsListView = findViewById<RecyclerView>(R.id.actorRecyclerView)
        actorsListView.layoutManager = LinearLayoutManager(this)
        actorsListView.adapter = actorsAdapter

        actorsCreationButton.setOnClickListener {
            val intent = Intent(this, AddActorActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onActorUpdateClick(actor: Actor) {
        val intent = Intent(this, ActorUpdateActivity::class.java)
        intent.putExtra("actor_id", actor.id)
        startActivity(intent)
    }

    override fun onActorDelete(actor: Actor) {
        dbHelper.eliminarActor(actor.id)
        actorsAdapter.refreshActors(dbHelper.consultarTodosLosActores())
    }

    override fun onMoviesView(actor: Actor) {
        val intent = Intent(this, MoviesListActivity::class.java)
        intent.putExtra("actor_id", actor.id)
        startActivity(intent)
    }

    override fun onActorView(actor: Actor) {
        val intent = Intent(this, ViewActorsBirthplaceActivity::class.java)
        intent.putExtra("actor_id", actor.id)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        actorsAdapter.refreshActors(dbHelper.consultarTodosLosActores())
    }
}