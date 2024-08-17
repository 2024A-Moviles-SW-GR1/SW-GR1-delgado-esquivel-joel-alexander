package com.example.plancar_moviles

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)

        // Obtener el nombre de usuario del UserManager
        val username = UserManager.getCurrentUser()?.username ?: "Usuario"
        val tvUsername: TextView = findViewById(R.id.tv_username)

        // Configurar el nombre de usuario en el TextView
        tvUsername.text = username


        // Configuración para editar perfil
        val btnEditarPerfil: LinearLayout = findViewById(R.id.linearLayout_editar_perfil)
        btnEditarPerfil.setOnClickListener {
            Toast.makeText(this, "Por implementar: edición de perfil", Toast.LENGTH_SHORT).show()
        }

        // Configuración para notificaciones
        val btnNotificaciones: LinearLayout = findViewById(R.id.linearLayout_notificaciones)
        btnNotificaciones.setOnClickListener {
            Toast.makeText(this, "Por implementar: notificaciones", Toast.LENGTH_SHORT).show()
        }

        // COnfiguración para políticas de privacidad
        val btnPoliticasPrivacidad: LinearLayout = findViewById(R.id.linearLayout_politicas_privacidad)
        btnPoliticasPrivacidad.setOnClickListener {
            Toast.makeText(this, "Por implementar: políticas de privacidad", Toast.LENGTH_SHORT).show()
        }

        // COnfiguración para cerrar sesión
        val btnCerrarSesion: LinearLayout = findViewById(R.id.linearLayout_cerrar_sesion)
        btnCerrarSesion.setOnClickListener {
            UserManager.logout()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Configuración del icono "Servicios" en el footer
        val iconServicios = findViewById<ImageView>(R.id.icon_servicios)
        iconServicios.setOnClickListener {
            val intent = Intent(this, ServiciosActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)  // Para una transición suave sin animaciones
        }

        // Configuración del icono "Cuenta" si es necesario
        val iconCuenta = findViewById<ImageView>(R.id.icon_citas)
        iconCuenta.setOnClickListener {
            val intent = Intent(this, MisCitasActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)  // Para una transición suave sin animaciones
        }

    }
}