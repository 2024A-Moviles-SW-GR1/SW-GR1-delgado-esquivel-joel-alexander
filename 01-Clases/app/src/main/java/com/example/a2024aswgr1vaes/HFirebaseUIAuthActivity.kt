package com.example.a2024aswgr1vaes

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class HFirebaseUIAuthActivity : AppCompatActivity() {

    private val respuestaLoginAuthUI = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res: FirebaseAuthUIAuthenticationResult ->
        if (res.resultCode == RESULT_OK) {
            if (res.idpResponse != null) {
                seLogeo(res.idpResponse!!)
            }
        }
    }

    fun seLogeo(response: IdpResponse) {
        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)
        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)
        val tvBienvenido = findViewById<TextView>(R.id.tv_bienvenido)
        tvBienvenido.text = FirebaseAuth.getInstance().currentUser?.displayName

        btnLogin.visibility = Button.INVISIBLE
        btnLogout.visibility = Button.VISIBLE

        if (response.isNewUser) {
            registrarUsuarioPorprimeraVez(response)
        }
    }

    private fun registrarUsuarioPorprimeraVez(response: IdpResponse) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)
        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)

        btnLogin.setOnClickListener{
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build()
            )
            val logearseIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            respuestaLoginAuthUI.launch(logearseIntent)
        }

        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)
        val tvBienvenida = findViewById<TextView>(R.id.tv_bienvenido)
        btnLogout.setOnClickListener{
            tvBienvenida.text = "Bienvenido"
            btnLogout.visibility = Button.INVISIBLE
            btnLogin.visibility = Button.VISIBLE
            FirebaseAuth.getInstance().signOut()
        }
        val usuario = FirebaseAuth.getInstance().currentUser
        if (usuario != null) {
            tvBienvenida.text = FirebaseAuth.getInstance().currentUser?.displayName
            btnLogin.visibility = Button.INVISIBLE
            btnLogout.visibility = Button.VISIBLE
        }

    }
}