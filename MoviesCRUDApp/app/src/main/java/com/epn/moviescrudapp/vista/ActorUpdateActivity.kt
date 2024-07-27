package com.epn.moviescrudapp.vista

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.epn.moviescrudapp.Actor
import com.epn.moviescrudapp.ESqliteActorHelper
import com.epn.moviescrudapp.R

class ActorUpdateActivity : AppCompatActivity() {

    private lateinit var dbHelper: ESqliteActorHelper
    private var actorId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor_update)
        dbHelper = ESqliteActorHelper(this)

        val editTextActorName: EditText = findViewById(R.id.editTextActorName)
        val editTextActorSurname: EditText = findViewById(R.id.editTextActorSurname)
        val editTextActorAge: EditText = findViewById(R.id.editTextActorAge)
        val editTextActorNationality: EditText = findViewById(R.id.editTextActorNationality)
        val switchActorActive: SwitchCompat = findViewById(R.id.switchActorActive)
        val actorEditButton: Button = findViewById(R.id.actorEditButton)

        //Se obtiene el id desde la actividad anterior
        actorId = intent.getIntExtra("actor_id", -1)
        if (actorId == -1) {
            finish()
            return
        }

        //Se obtiene el actor por el id
        val actor = dbHelper.consultarActorPorId(actorId)

        //Se llenan los campos con los datos del actor
        editTextActorName.setText(actor?.name)
        editTextActorSurname.setText(actor?.lastName)
        editTextActorAge.setText(actor?.age.toString())
        editTextActorNationality.setText(actor?.nationality)
        switchActorActive.isChecked = actor?.active ?: false

        //Se crea el evento click del boton
        actorEditButton.setOnClickListener {
            val newName = editTextActorName.text.toString()
            val newLastName = editTextActorSurname.text.toString()
            val newAge = editTextActorAge.text.toString().toIntOrNull() ?: 0
            val newNationality = editTextActorNationality.text.toString()
            val newActive = switchActorActive.isChecked

            val updatedActor = Actor(actorId, newName, newLastName, newAge, newNationality, newActive)
            dbHelper.actualizarActor(updatedActor)
            finish()
            Toast.makeText(this, "Actor actualizado correctamente", Toast.LENGTH_SHORT).show()
        }


    }
}