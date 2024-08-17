package com.epn.examenmoviescrudapp.vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.epn.examenmoviescrudapp.Actor
import com.epn.examenmoviescrudapp.ESqliteActorHelper
import com.epn.examenmoviescrudapp.R

class ActorUpdateActivity : AppCompatActivity() {

    private lateinit var dbHelper: ESqliteActorHelper
    private var actorId: Int = -1
    private var latitud: Double = 0.0
    private var longitud: Double = 0.0
    private val REQUEST_CODE_UBICACION = 1

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
        val actualizarUbicacionButton: Button = findViewById(R.id.actualizarUbicacionButton)

        actualizarUbicacionButton.setOnClickListener {
            val intent = Intent(this, SelectUbicationActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_UBICACION)
        }

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

            val updatedActor = Actor(actorId, newName, newLastName, newAge, newNationality, newActive, latitud, longitud)
            dbHelper.actualizarActor(updatedActor)
            finish()
            Toast.makeText(this, "Actor actualizado correctamente", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_UBICACION && resultCode == RESULT_OK) {
            latitud = data?.getDoubleExtra("latitud", 0.0) ?: 0.0
            longitud = data?.getDoubleExtra("longitud", 0.0) ?: 0.0
            Toast.makeText(this, "Ubicación seleccionada: ($latitud, $longitud)", Toast.LENGTH_SHORT).show()
        }
    }

}