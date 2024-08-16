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

class AddActorActivity : AppCompatActivity() {

    private lateinit var dbHelper: ESqliteActorHelper
    private var latitud: Double = 0.0
    private var longitud: Double = 0.0
    private val REQUEST_CODE_UBICACION = 1

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
        val actorBornDirectionButton: Button = findViewById(R.id.aniadirUbicacionButton)

        actorBornDirectionButton.setOnClickListener {
            val intent = Intent(this, SelectUbicationActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_UBICACION)
        }

        actorCreationButton.setOnClickListener {
            val name = editTextActorName.text.toString()
            val lastName = editTextActorSurname.text.toString()
            val age = editTextActorAge.text.toString().toIntOrNull() ?: 0
            val nationality = editTextActorNationality.text.toString()
            val active = switchActorActive.isChecked

            val actor = Actor(0, name, lastName, age, nationality, active, latitud, longitud)
            dbHelper.crearActor(actor)
            finish()
            Toast.makeText(this, "Actor/Actriz guardado", Toast.LENGTH_SHORT).show()
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