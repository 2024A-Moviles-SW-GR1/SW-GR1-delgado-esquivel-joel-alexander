package com.epn.examenmoviescrudapp.vista

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.epn.examenmoviescrudapp.ESqliteActorHelper
import com.epn.examenmoviescrudapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ViewActorsBirthplaceActivity : AppCompatActivity() {

    private lateinit var dbHelper: ESqliteActorHelper
    private var actorId: Int = -1
    private lateinit var mapa: GoogleMap
    private var marker: LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_actors_birthplace)
        dbHelper = ESqliteActorHelper(this)

        //Se obtiene el id desde la actividad anterior
        actorId = intent.getIntExtra("actor_id", -1)
        if (actorId == -1) {
            finish()
            return
        }
        //Se obtiene el actor por el id
        val actor = dbHelper.consultarActorPorId(actorId)
        val latitud = actor?.latitud
        val longitud = actor?.longitud

        iniciarLogicaDeMapa(latitud, longitud)

        // Configurar el botón de cancelar
        val btnRegresar: Button = findViewById(R.id.btn_regresar_lista_actores)
        btnRegresar.setOnClickListener {
            val intent = Intent(this, ActorsListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun iniciarLogicaDeMapa(latitud: Double?, longitud: Double?) {
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_visualizar_ubicacion_actor) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            with(googleMap) {
                mapa = googleMap
                establecerConfiguracionesMapa()
                // Configurar un marcador que se puede mover
                val initialLatLng = LatLng(latitud ?: 0.0, longitud ?: 0.0)
                mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLatLng, 12f))
                mapa.addMarker(MarkerOptions().position(initialLatLng).title("Lugar de nacimiento"))

            }
        }
    }

    fun establecerConfiguracionesMapa() {
        val contexto = this.applicationContext
        with(mapa) {
            val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
            val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
            val permisoFine = ContextCompat.checkSelfPermission(contexto, nombrePermisoFine)
            val permisoCoarse = ContextCompat.checkSelfPermission(contexto, nombrePermisoCoarse)
            val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED &&
                    permisoCoarse == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }
}