package com.example.meteorologia.Vistas
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meteorologia.MainActivity
import com.example.meteorologia.cambiar_password
import com.example.meteorologia.databinding.FragmentPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.meteorologia.crearProducto
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PerfilFragment : Fragment() {

    // Configuración de viewBinding
    private lateinit var binding: FragmentPerfilBinding

    // Configuración de Firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inicializar viewBinding
        binding = FragmentPerfilBinding.inflate(inflater, container, false)

        // Inicializar Firebase
        auth = Firebase.auth

        // Configurar el botón de logout
        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro de que deseas cerrar sesión?")
                .setNeutralButton("Cancelar") { dialog, which ->
                    // Boton cancelar
                }
                .setPositiveButton("Aceptar") { dialog, which ->
                    signOut()
                }
                .show()
        }

        binding.btnCambiarPwd.setOnClickListener {
            val intent = Intent(requireContext(), cambiar_password::class.java)
            startActivity(intent)
        }

        // Configurar el botón de agregar datos
        binding.btnAgregarDatos.setOnClickListener {
            val intent = Intent(requireContext(), crearProducto::class.java)
            startActivity(intent)
        }


        // Devolver la vista inflada
        return binding.root


    }




    private fun signOut() {
        // Cerrar sesión con Firebase
        Firebase.auth.signOut()

        // Redirigir al usuario a la pantalla de inicio de sesión
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)

        // Finalizar la actividad actual para que el usuario no pueda regresar al fragmento de perfil
        requireActivity().finish()  // Llamar a finish() sobre la actividad que contiene el fragmento
    }
}
