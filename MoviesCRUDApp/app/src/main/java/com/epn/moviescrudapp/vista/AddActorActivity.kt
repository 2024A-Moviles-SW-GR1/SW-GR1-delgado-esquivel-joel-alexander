package com.epn.moviescrudapp.vista

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.epn.moviescrudapp.Actor
import com.epn.moviescrudapp.ESqliteActorHelper
import com.epn.moviescrudapp.ESqliteMovieHelper
import com.epn.moviescrudapp.Movie
import com.epn.moviescrudapp.R

class AddActorActivity : AppCompatActivity() {

    private lateinit var dbHelper: ESqliteActorHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_actor)

        dbHelper = ESqliteActorHelper(this)

        val editTextActorName: EditText = findViewById(R.id.editTextActorName)
        val editTextActorSurname: EditText = findViewById(R.id.editTextActorSurname)
        val editTextActorAge: EditText = findViewById(R.id.editTextActorAge)
        val editTextActorNationality: EditText = findViewById(R.id.editTextActorNationality)
        val switchActorActive: SwitchCompat = findViewById(R.id.switchActorActive)
        val actorCreationButton: Button = findViewById(R.id.actorCreationButton)

        actorCreationButton.setOnClickListener {
            val name = editTextActorName.text.toString()
            val lastName = editTextActorSurname.text.toString()
            val age = editTextActorAge.text.toString().toIntOrNull() ?: 0
            val nationality = editTextActorNationality.text.toString()
            val active = switchActorActive.isChecked

            val actor = Actor(0, name, lastName, age, nationality, active)
            dbHelper.crearActor(actor)
            finish()
            Toast.makeText(this, "Actor/Actriz guardado", Toast.LENGTH_SHORT).show()

        }

    }
}