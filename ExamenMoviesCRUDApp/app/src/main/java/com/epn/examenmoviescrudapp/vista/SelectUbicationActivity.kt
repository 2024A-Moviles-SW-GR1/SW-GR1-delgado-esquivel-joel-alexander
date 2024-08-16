package com.epn.examenmoviescrudapp.vista

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.epn.examenmoviescrudapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SelectUbicationActivity : AppCompatActivity() {

    private lateinit var mapa: GoogleMap
    private var marker: LatLng? = null
    private var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_ubication)

        solicitarPermisos()
        iniciarLogicaDeMapa()

        // Configurar el botón de guardar
        val btnGuardarUbiNacimiento: Button = findViewById(R.id.btn_guardar_ubi_nacimiento)
        btnGuardarUbiNacimiento.setOnClickListener {
            marker?.let {
                val intent = Intent()
                intent.putExtra("latitud", it.latitude)
                intent.putExtra("longitud", it.longitude)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        // Configurar el botón de cancelar
        val btnCancelar: Button = findViewById(R.id.btn_cancel_agregar_ubi_nacimiento)
        btnCancelar.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

    }


    /*Configuraciones iniciales del mapa*/

    private fun iniciarLogicaDeMapa() {
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_actor_agregar_ubicacion) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            with(googleMap) {
                mapa = googleMap
                establecerConfiguracionesMapa()
                // Configurar un marcador que se puede mover
                val initialLatLng = LatLng(-0.180653, -78.467834) // Coordenadas iniciales (Quito)
                mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLatLng, 12f))

                mapa.setOnMapClickListener { latLng ->
                    marker?.let { mapa.clear() }
                    marker = latLng
                    mapa.addMarker(MarkerOptions().position(latLng).title("Lugar de nacimiento"))
                }
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

    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
        val permisoFine = ContextCompat.checkSelfPermission(contexto, nombrePermisoFine)
        val permisoCoarse = ContextCompat.checkSelfPermission(contexto, nombrePermisoCoarse)
        val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED &&
                permisoCoarse == PackageManager.PERMISSION_GRANTED
        if (tienePermisos) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(nombrePermisoFine, nombrePermisoCoarse), 1
            )
        }
    }

}