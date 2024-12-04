package com.example.meteorologia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meteorologia.databinding.ActivityCambiarPasswordBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class cambiar_password : AppCompatActivity() {
    // Activar viewBinding
    private lateinit var binding:ActivityCambiarPasswordBinding
    // Activar Firebase Auth
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambiarPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar firebase auth
        auth = Firebase.auth

        binding.btnVolver.setOnClickListener{
            intent = Intent(this, perfil::class.java)
            startActivity(intent)
        }

        binding.btnActualizarPwd.setOnClickListener{

            // Obtener usuario actual
            val user = auth.currentUser!!
            val contra1:String = binding.etPwd1.text.toString()
            val contra2:String = binding.etPassword.text.toString()
            val contra3:String = binding.etPassword2.text.toString()

            // Validar que los campos no sean nulos

            // Reconectar al usuario actual

            val credential = EmailAuthProvider.getCredential(user.email!!, contra1)
            user.reauthenticate(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Actualizar la contraseña del usuario
                        if (contra2 == contra3) {
                            user!!.updatePassword(contra2)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this, "Contraseña actualizada", Toast.LENGTH_SHORT).show()// Datos actualizados
                                        intent = Intent(this, perfil::class.java)
                                        startActivity(intent)
                                    }
                                }
                        } else {
                            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "Error al actualizar la contraseña",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

        }

    }

}